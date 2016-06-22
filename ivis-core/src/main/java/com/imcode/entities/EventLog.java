package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.User;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by vitaly on 15.10.15.
 */

public class EventLog extends AbstractIdEntity<Long>{
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @Column
    @JsonProperty("date_time")
    private LocalDateTime dateTime;

    @Column
    private String action;

    @Column
    private String description;

}
