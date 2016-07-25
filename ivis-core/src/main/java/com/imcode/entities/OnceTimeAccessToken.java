package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ruslan on 25.07.16.
 */
@Entity
@Table(name = "dbo_once_time_access_token")
public class OnceTimeAccessToken extends AbstractIdEntity<Long> implements Serializable {

    @Column
    private String token;

    @Column(name = "not_persist_user")
    @Lob
    private Serializable user;

    @Column(name = "expiry_day")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column
    private Boolean used = false;

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

    public Serializable getUser() {
        return user;
    }

    public void setUser(Serializable user) {
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

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public static OnceTimeAccessToken genToken(User user, int expiration, OnceTimeAccessToken.TokenType type) {
        OnceTimeAccessToken token = new OnceTimeAccessToken();

        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(calculateExpiryDate(expiration));
        token.setTokenType(type);

        return token;
    }

    private static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public boolean isExpired() {
        return expiryDate.after(new Date()) ;
    }
}
