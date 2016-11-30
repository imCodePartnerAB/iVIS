package com.imcode.entities.oauth2;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;

/**
 * Created by vitaly on 22.05.15.
 */
//@Embeddable
public class JpaRefreshToken implements Serializable, OAuth2RefreshToken{
    private static final long serialVersionUID = 8349970621900575838L;

//    @Column(length = 32)
    private String value;

    /**
     * Create a new refresh token.
     */
    @JsonCreator
    public JpaRefreshToken(String value) {
        this.value = value;
    }

    /**
     * Default constructor for JPA and other serialization tools.
     */
    @SuppressWarnings("unused")
    public JpaRefreshToken() {
        this(null);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.oauth2.common.IFOO#getValue()
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JpaRefreshToken)) {
            return false;
        }

        JpaRefreshToken that = (JpaRefreshToken) o;

        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
