package com.imcode.entities.oauth2;

import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by vitaly on 22.05.15.
 */
@Entity
@Table(name = "dbo_oauth_access_token")
public class JpaAccessToken implements Serializable, OAuth2AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date expiration;

    @Column
    private String tokenType = BEARER_TYPE.toLowerCase();

    @Basic()
    private JpaRefreshToken refreshToken;
//    private OAuth2RefreshToken refreshToken;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dbo_oauth_token_scope", joinColumns = @JoinColumn(name = "tokenId"))
    private Set<String> scope;

    @Transient
    private Map<String, Object> additionalInformation = Collections.emptyMap();

    /**
     * Create an access token from the value provided.
     */
    public JpaAccessToken(String value) {
        this.value = value;
    }

    /**
     * Private constructor for JPA and other serialization tools.
     */
    @SuppressWarnings("unused")
    public JpaAccessToken() {
        this((String) null);
    }

    /**
     * Copy constructor for access token.
     *
     * @param accessToken
     */
    public JpaAccessToken(OAuth2AccessToken accessToken) {
        this(accessToken.getValue());
        setAdditionalInformation(accessToken.getAdditionalInformation());
        setRefreshToken(accessToken.getRefreshToken());
        setExpiration(accessToken.getExpiration());
        setScope(accessToken.getScope());
        setTokenType(accessToken.getTokenType());
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * The token value.
     *
     * @return The token value.
     */
    public String getValue() {
        return value;
    }

    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                .intValue() : 0;
    }

    protected void setExpiresIn(int delta) {
        setExpiration(new Date(System.currentTimeMillis() + delta));
    }

    /**
     * The instant the token expires.
     *
     * @return The instant the token expires.
     */
    public Date getExpiration() {
        return expiration;
    }

    /**
     * The instant the token expires.
     *
     * @param expiration The instant the token expires.
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    /**
     * Convenience method for checking expiration
     *
     * @return true if the expiration is befor ethe current time
     */
    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }

    /**
     * The token type, as introduced in draft 11 of the OAuth 2 spec. The spec doesn't define (yet) that the valid token
     * types are, but says it's required so the default will just be "undefined".
     *
     * @return The token type, as introduced in draft 11 of the OAuth 2 spec.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * The token type, as introduced in draft 11 of the OAuth 2 spec.
     *
     * @param tokenType The token type, as introduced in draft 11 of the OAuth 2 spec.
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * The refresh token associated with the access token, if any.
     *
     * @return The refresh token associated with the access token, if any.
     */
    public OAuth2RefreshToken getRefreshToken() {
        return refreshToken;
    }

    /**
     * The refresh token associated with the access token, if any.
     *
     * @param refreshToken The refresh token associated with the access token, if any.
     */
    public void setRefreshToken(OAuth2RefreshToken refreshToken) {
        this.refreshToken = (JpaRefreshToken) refreshToken;
    }

    /**
     * The scope of the token.
     *
     * @return The scope of the token.
     */
    public Set<String> getScope() {
        return scope;
    }

    /**
     * The scope of the token.
     *
     * @param scope The scope of the token.
     */
    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    public static OAuth2AccessToken valueOf(Map<String, String> tokenParams) {
        JpaAccessToken token = new JpaAccessToken(tokenParams.get(ACCESS_TOKEN));

        if (tokenParams.containsKey(EXPIRES_IN)) {
            long expiration = 0;
            try {
                expiration = Long.parseLong(String.valueOf(tokenParams.get(EXPIRES_IN)));
            } catch (NumberFormatException e) {
                // fall through...
            }
            token.setExpiration(new Date(System.currentTimeMillis() + (expiration * 1000L)));
        }

        if (tokenParams.containsKey(REFRESH_TOKEN)) {
            String refresh = tokenParams.get(REFRESH_TOKEN);
            DefaultOAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(refresh);
            token.setRefreshToken(refreshToken);
        }

        if (tokenParams.containsKey(SCOPE)) {
            Set<String> scope = new TreeSet<String>();
            for (StringTokenizer tokenizer = new StringTokenizer(tokenParams.get(SCOPE), " ,"); tokenizer
                    .hasMoreTokens(); ) {
                scope.add(tokenizer.nextToken());
            }
            token.setScope(scope);
        }

        if (tokenParams.containsKey(TOKEN_TYPE)) {
            token.setTokenType(tokenParams.get(TOKEN_TYPE));
        }

        return token;
    }

    /**
     * Additional information that token granters would like to add to the token, e.g. to support new token types.
     *
     * @return the additional information (default empty)
     */
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Additional information that token granters would like to add to the token, e.g. to support new token types. If
     * the values in the map are primitive then remote communication is going to always work. It should also be safe to
     * use maps (nested if desired), or something that is explicitly serializable by Jackson.
     *
     * @param additionalInformation the additional information to set
     */
    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = new LinkedHashMap<String, Object>(additionalInformation);
    }

}


