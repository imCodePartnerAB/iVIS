package com.imcode.entities.embed;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created by vitaly on 15.10.15.
 */
@Embeddable
public class Decision {
    public static enum Status {APPROVE, DENI, SUBMIT}

    @Column
    @Enumerated(EnumType.STRING)
    public Status status;

    @Column
    public Date date;

    @Column
    public String comment;

    public Decision() {
        this(Status.SUBMIT, new Date(), "");
    }

    public Decision(Status status, Date date, String comment) {
        this.status = status;
        this.date = date;
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void approve() {
        applyStatus(Status.APPROVE);
    }

    public void deni() {
        applyStatus(Status.DENI);
    }

    public void submit() {
        applyStatus(Status.SUBMIT);
    }

//    ----------------------------------------------------------------------------

    private void applyStatus(Status status) {
        this.status = status;
        this.date = new Date();
    }



    @Override
    public String toString() {
        return status.toString();
    }
}
