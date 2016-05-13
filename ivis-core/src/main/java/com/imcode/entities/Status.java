package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruslan on 5/6/16.
 */
@Entity
@Table(name = "dbo_status")
public class Status extends AbstractIdEntity<Long> implements Serializable {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    public Status() {
    }

    public enum State {
        CREATING,
        NEW,
        ASSIGNED,
        ARCHIVED
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
