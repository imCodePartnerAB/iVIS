package com.imcode.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by vitaly on 09.02.15.
 */

@Entity
@Table(name="dbo_application")
public class _Application {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_appTablesPermissions",
            joinColumns = {@JoinColumn(name = "application")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<AppTablePermission> tablePermissions;
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("SchoolClass{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", school=").append(school);
//        sb.append('}');
//        return sb.toString();
//    }

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

    public List<AppTablePermission> getTablePermissions() {
        return tablePermissions;
    }

    public void setTablePermissions(List<AppTablePermission> tablePermissions) {
        this.tablePermissions = tablePermissions;
    }

    //    public School getSchool() {
//        return school;
//    }
//
//    public void setSchool(School school) {
//        this.school = school;
//    }
}
