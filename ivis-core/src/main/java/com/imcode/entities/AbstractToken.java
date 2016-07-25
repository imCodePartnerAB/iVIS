package com.imcode.entities;

import com.imcode.entities.User;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruslan on 25.07.16.
 */
public abstract class AbstractToken extends AbstractIdEntity<Long> implements Serializable {

    @Column
    protected String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    protected User user;

    @Column(name = "expiry_day")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date expiryDate;

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

}
