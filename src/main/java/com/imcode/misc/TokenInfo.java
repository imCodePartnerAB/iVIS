package com.imcode.misc;

import com.imcode.entities.Application;
import com.imcode.entities.User;

import java.util.Date;
import java.util.UUID;

/**
 * Created by vitaly on 25.02.15.
 */
public class TokenInfo {
    private UUID id;
    private User user;
    private Application application;
    private Date receiptDate;
    private Long lifeTime;
    private UUID refreshToken;

    public TokenInfo(User user, Application application, Long lifeTime) {
        this.user = user;
        this.application = application;
        this.lifeTime = lifeTime;

        id = UUID.randomUUID();
        receiptDate = new Date();
        refreshToken = UUID.randomUUID();
    }

    public boolean isRotten() {
        Date dateOfDeath = new Date(getReceiptDate().getTime() + getLifeTime());
        Date now = new Date();

        return now.after(dateOfDeath);
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Application getApplication() {
        return application;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public Long getLifeTime() {
        return lifeTime;
    }

    public UUID getRefreshToken() {
        return refreshToken;
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
