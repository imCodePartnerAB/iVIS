package com.imcode.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_truancy")
public class Truancy extends AbstractIdEntity{
    @ManyToOne
    @JoinColumn(name = "pupilId")
    private Pupil pupil;

    @Temporal(TemporalType.DATE)
    @Column
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column
    private Date endDate;

    public Truancy() { }

    public Truancy(Pupil pupil, Date startDate, Date endDate) {
        this.pupil = pupil;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
