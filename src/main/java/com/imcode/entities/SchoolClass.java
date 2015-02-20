package com.imcode.entities;

import javax.persistence.*;

/**
 * Created by vitaly on 09.02.15.
 */

@Entity
@Table(name="dbo_schoolClass")
public class SchoolClass {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "school")
    private School school;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SchoolClass{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", school=").append(school);
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
