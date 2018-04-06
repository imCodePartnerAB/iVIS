package com.imcode.saml2.auth.server;

import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.saml2.core.*;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.schema.impl.XSStringBuilder;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.signature.*;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.UUID;

public class SAMLBuilder {

    private static final XMLObjectBuilderFactory builderFactory = Configuration.getBuilderFactory();

    @SuppressWarnings({"unused", "unchecked"})
    static <T> T buildSAMLObject(final Class<T> objectClass, final QName qName) {
        return (T) builderFactory.getBuilder(qName).buildObject(qName);
    }

    static Issuer buildIssuer(final String issuingEntityName) {
        final Issuer issuer = buildSAMLObject(Issuer.class, Issuer.DEFAULT_ELEMENT_NAME);
        issuer.setValue(issuingEntityName);
        issuer.setFormat(NameIDType.ENTITY);

        return issuer;
    }

    static Status buildStatus(final String value) {

        final StatusCode statusCode = buildSAMLObject(StatusCode.class, StatusCode.DEFAULT_ELEMENT_NAME);
        statusCode.setValue(value);

        final Status status = buildSAMLObject(Status.class, Status.DEFAULT_ELEMENT_NAME);
        status.setStatusCode(statusCode);

        return status;
    }

    static Assertion buildAssertion(final SAMLPrincipal principal, final Status status, final String entityId) {
        final Assertion assertion = buildSAMLObject(Assertion.class, Assertion.DEFAULT_ELEMENT_NAME);

        if (status.getStatusCode().getValue().equals(StatusCode.SUCCESS_URI)) {
            final Subject subject = buildSubject(
                    principal.getNameID(), principal.getNameIDType(),
                    principal.getAssertionConsumerServiceURL(), principal.getRequestID());

            assertion.setSubject(subject);
        }

        final Audience audience = buildSAMLObject(Audience.class, Audience.DEFAULT_ELEMENT_NAME);
        audience.setAudienceURI(principal.getServiceProviderEntityID());

        final AudienceRestriction audienceRestriction = buildSAMLObject(
                AudienceRestriction.class, AudienceRestriction.DEFAULT_ELEMENT_NAME
        );

        audienceRestriction.getAudiences().add(audience);

        final Conditions conditions = buildSAMLObject(Conditions.class, Conditions.DEFAULT_ELEMENT_NAME);
        conditions.getAudienceRestrictions().add(audienceRestriction);

        assertion.setConditions(conditions);
        assertion.setIssuer(buildIssuer(entityId));
        assertion.getAuthnStatements().add(buildAuthnStatement(new DateTime(), entityId));
        assertion.getAttributeStatements().add(buildAttributeStatement(principal.getAttributes()));
        assertion.setID(randomSAMLId());
        assertion.setIssueInstant(new DateTime());

        return assertion;
    }

    private static Subject buildSubject(final String subjectNameId, final String subjectNameIdType,
                                        final String recipient, final String inResponseTo) {

        final NameID nameID = buildSAMLObject(NameID.class, NameID.DEFAULT_ELEMENT_NAME);
        nameID.setValue(subjectNameId);
        nameID.setFormat(subjectNameIdType);

        final Subject subject = buildSAMLObject(Subject.class, Subject.DEFAULT_ELEMENT_NAME);
        subject.setNameID(nameID);

        final SubjectConfirmation subjectConfirmation = buildSAMLObject(
                SubjectConfirmation.class, SubjectConfirmation.DEFAULT_ELEMENT_NAME
        );

        subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);

        final SubjectConfirmationData subjectConfirmationData = buildSAMLObject(
                SubjectConfirmationData.class, SubjectConfirmationData.DEFAULT_ELEMENT_NAME
        );

        subjectConfirmationData.setRecipient(recipient);
        subjectConfirmationData.setInResponseTo(inResponseTo);
        subjectConfirmationData.setNotOnOrAfter(new DateTime().plusMinutes(8 * 60));
        subjectConfirmationData.setAddress(recipient);

        subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);

        subject.getSubjectConfirmations().add(subjectConfirmation);

        return subject;
    }

    private static AuthnStatement buildAuthnStatement(final DateTime authnInstant, final String entityID) {
        final AuthnContextClassRef authnContextClassRef = buildSAMLObject(
                AuthnContextClassRef.class, AuthnContextClassRef.DEFAULT_ELEMENT_NAME

        );

        authnContextClassRef.setAuthnContextClassRef(AuthnContext.PASSWORD_AUTHN_CTX);

        final AuthenticatingAuthority authenticatingAuthority = buildSAMLObject(
                AuthenticatingAuthority.class, AuthenticatingAuthority.DEFAULT_ELEMENT_NAME
        );

        authenticatingAuthority.setURI(entityID);

        final AuthnContext authnContext = buildSAMLObject(AuthnContext.class, AuthnContext.DEFAULT_ELEMENT_NAME);
        authnContext.setAuthnContextClassRef(authnContextClassRef);
        authnContext.getAuthenticatingAuthorities().add(authenticatingAuthority);

        final AuthnStatement authnStatement = buildSAMLObject(
                AuthnStatement.class, AuthnStatement.DEFAULT_ELEMENT_NAME
        );

        authnStatement.setAuthnContext(authnContext);
        authnStatement.setAuthnInstant(authnInstant);

        return authnStatement;
    }

    private static AttributeStatement buildAttributeStatement(final List<SAMLAttribute> attributes) {
        final AttributeStatement attributeStatement = buildSAMLObject(
                AttributeStatement.class, AttributeStatement.DEFAULT_ELEMENT_NAME
        );

        attributes.forEach(entry ->
                attributeStatement.getAttributes().add(buildAttribute(entry.getName(), entry.getValue())));

        return attributeStatement;
    }

    private static Attribute buildAttribute(final String name, final String value) {
        final Attribute attribute = buildSAMLObject(Attribute.class, Attribute.DEFAULT_ELEMENT_NAME);
        attribute.setName(name);
        attribute.setNameFormat("urn:oasis:names:tc:SAML:2.0:attrname-format:uri");

        final XSStringBuilder xssStringBuilder = (XSStringBuilder) Configuration.getBuilderFactory()
                .getBuilder(XSString.TYPE_NAME);

        final XSString xssStringValue = xssStringBuilder
                .buildObject(AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);

        xssStringValue.setValue(value);

        attribute.getAttributeValues().add(xssStringValue);

        return attribute;
    }

    static void signAssertion(final SignableXMLObject signableXMLObject, final Credential signingCredential)
            throws MarshallingException, SignatureException {

        final Signature signature = buildSAMLObject(Signature.class, Signature.DEFAULT_ELEMENT_NAME);
        signature.setSigningCredential(signingCredential);
        signature.setSignatureAlgorithm(
                Configuration.getGlobalSecurityConfiguration().getSignatureAlgorithmURI(signingCredential)
        );
        signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

        signableXMLObject.setSignature(signature);

        Configuration.getMarshallerFactory().getMarshaller(signableXMLObject).marshall(signableXMLObject);

        Signer.signObject(signature);
    }

    static String randomSAMLId() {
        return "_" + UUID.randomUUID().toString();
    }
}