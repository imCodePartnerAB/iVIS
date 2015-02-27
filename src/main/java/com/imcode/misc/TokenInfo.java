package com.imcode.misc;

import com.imcode.entities.Application;
import com.imcode.entities.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vitaly on 25.02.15.
 */
@Entity
@Table(name = "dbo_tokenInfo")
public class TokenInfo {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "application")
    private Application application;

    @Column
    private Date receiptDate;

    @Column
    private Long lifeTime;

    @Column(columnDefinition = "BINARY(16)")
    private UUID refreshToken;

    public TokenInfo() {
    }

    public TokenInfo(User user, Application application, Long lifeTime) {
        this.user = user;
        this.application = application;
        this.lifeTime = lifeTime;

        id = UUID.randomUUID();
        receiptDate = new Date();
        refreshToken = UUID.randomUUID();
    }

    public boolean isRotten() {
        Date dateOfDeath = new Date(getReceiptDate().getTime() + getLifeTime() * 1000);
        Date now = new Date();

        return now.after(dateOfDeath);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Long lifeTime) {
        this.lifeTime = lifeTime;
    }

    public UUID getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TokenInfo{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", application=").append(application);
        sb.append(", receiptDate=").append(receiptDate);
        sb.append(", lifeTime=").append(lifeTime);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append('}');
        return sb.toString();
    }
}
