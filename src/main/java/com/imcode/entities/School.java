package com.imcode.entities;

import javax.persistence.*;

@Entity
@Table(name="dbo_school")
public class School {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

//    @Column(name="name1")
//    private String name1;

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

//    public String getName1() {
//        return name1;
//    }
//
//    public void setName1(String name1) {
//        this.name1 = name1;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("School{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
