package com.imcode.saml2.auth.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class KeyStoreFactory {

    private static final Logger logger = LoggerFactory.getLogger(KeyStoreFactory.class);
    private static final CertificateFactory certificateFactory;

    static {
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            logger.error("Error during creating certificate factory.", e);
            throw new RuntimeException(e);
        }
    }

    public static KeyStore createKeyStore(final String entityId, final String idpPassword,
                                          final String certificate, final String privateKey) {

        try {
            final KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, idpPassword.toCharArray());

            KeyStoreFactory.addPrivateKey(keyStore, entityId, privateKey, certificate, idpPassword);

            return keyStore;
        } catch (Exception e) {
            logger.error("Error during creating key store.", e);
            throw new RuntimeException(e);
        }
    }

    private static void addPrivateKey(final KeyStore keyStore, final String entityId,
                                      final String privateKeyValue, final String certificateValue,
                                      final String idpPassword) throws Exception {

        final byte[] decodedKey = Base64.getDecoder().decode(privateKeyValue.getBytes());

        final Key privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decodedKey));

        final String wrappedCertificate = wrapCertificate(certificateValue);

        final Certificate certificate = certificateFactory.generateCertificate(
                new ByteArrayInputStream(wrappedCertificate.getBytes())
        );

        final char[] passwordChars = idpPassword.toCharArray();

        keyStore.setKeyEntry(entityId, privateKey, passwordChars, new Certificate[]{certificate});
    }

    private static String wrapCertificate(String certificate) {
        return "-----BEGIN CERTIFICATE-----\n" + certificate + "\n-----END CERTIFICATE-----";
    }
}
