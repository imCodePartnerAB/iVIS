package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.superclasses.AbstractIdEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_truancy")
public class Truancy extends AbstractIdEntity<Long> implements Serializable {
    @ManyToOne
    @JoinColumn(name = "pupilId")
    private Pupil pupil;

    @Temporal(TemporalType.DATE)
    @Column
    @JsonProperty("start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column
    @JsonProperty("end_date")
    private Date endDate;

    public Truancy() { }

    public Truancy(Pupil pupil, Date startDate, Date endDate) {
        this.pupil = pupil;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @JsonIgnoreProperties(value = "truancies")
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
