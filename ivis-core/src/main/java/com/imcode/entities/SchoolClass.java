package com.imcode.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
/**
 * Created by vitaly on 09.02.15.
 */

@Entity
@Table(name="dbo_school_class")
public class SchoolClass extends AbstractNamedEntity  implements Serializable {

    @Temporal(TemporalType.TIME)
    @Column
    private Date schoolDayStart;

    @Temporal(TemporalType.TIME)
    @Column
    private Date schoolDayEnd;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "school")
    private School school;

    @OneToMany(mappedBy = "schoolClass")
    private Set<Pupil> pupils;


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
}
