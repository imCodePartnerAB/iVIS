package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imcode.entities.embed.Diary;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by vitaly on 09.02.15.
 */

@Entity
@Table(name = "dbo_school_class")
public class SchoolClass extends AbstractNamedEntity<Long> implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "school_day_start")
    private Date schoolDayStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column(name = "school_day_end")
    private Date schoolDayEnd;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @JsonIgnoreProperties(value = "school_classes")
    private School school;

    @Column(name = "next_cloud_enabled", columnDefinition = "BIT DEFAULT FALSE")
    private Boolean nextCloudEnabled = false;

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.EAGER)
    private Set<Pupil> pupils;

    @ElementCollection
    @CollectionTable(name = "dbo_school_class_diaries", joinColumns = @JoinColumn(name = "owner_id"))
    @OrderBy("dayOfWeek")
    private Set<Diary> diaries;


    public SchoolClass() {
    }

    public SchoolClass(String name, School school, Date schoolDayStart, Date schoolDayEnd) {
        super(name);
        this.school = school;
        this.schoolDayStart = schoolDayStart;
        this.schoolDayEnd = schoolDayEnd;
    }

    public SchoolClass(String name) {
        super(name);
    }

    public Date getSchoolDayStart() {
        return schoolDayStart;
    }

    public void setSchoolDayStart(Date schoolDayStart) {
        this.schoolDayStart = schoolDayStart;
    }

    public Date getSchoolDayEnd() {
        return schoolDayEnd;
    }

    public void setSchoolDayEnd(Date schoolDayEnd) {
        this.schoolDayEnd = schoolDayEnd;
    }

    @JsonIgnoreProperties(value = "school_classes")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

    public Set<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(Set<Diary> diaries) {
        this.diaries = diaries;
    }

    @JsonIgnore
    public List<Diary> getDiaryList() {
        return new LinkedList<>(diaries);
    }

    @JsonIgnore
    public void setDiaryList(List<Diary> diaries) {
        this.diaries.addAll(diaries);
    }

    @Override
    public String toString() {
        return name;
    }

    public Boolean getNextCloudEnabled() {
        return nextCloudEnabled;
    }

    public void setNextCloudEnabled(Boolean nextCloudEnabled) {
        this.nextCloudEnabled = nextCloudEnabled;
    }

    //    public Set<Pupil> getPupils() {
//        return pupils;
//    }
//
//    public void setPupils(Set<Pupil> pupils) {
//        this.pupils = pupils;
//    }
}
