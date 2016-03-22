package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.Diary;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by vitaly on 09.02.15.
 */

@Entity
@Table(name = "dbo_school_class")
public class SchoolClass extends AbstractNamedEntity<Long> implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column
    private Date schoolDayStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    @Column
    private Date schoolDayEnd;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "school")
    private School school;

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.EAGER)
    private Set<Pupil> pupils;

    @ElementCollection
    @CollectionTable(name = "dbo_school_class_diaries", joinColumns = @JoinColumn(name = "ownerId"))
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

    //    public Set<Pupil> getPupils() {
//        return pupils;
//    }
//
//    public void setPupils(Set<Pupil> pupils) {
//        this.pupils = pupils;
//    }
}
