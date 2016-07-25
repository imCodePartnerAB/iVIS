package com.imcode.entities;

import com.imcode.entities.User;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruslan on 25.07.16.
 */
public abstract class TypedAccessToken extends AbstractIdEntity<Long> implements Serializable {

    @Column
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiry_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    public enum TokenType {
        VERIFICATION("Verification token"), PASSWORD_RESET("Password reset token");
        private final String description;

        TokenType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
