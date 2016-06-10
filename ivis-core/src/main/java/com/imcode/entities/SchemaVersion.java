package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruslan on 09.06.16.
 */
@Entity
@Table(name = "schema_version")
public class SchemaVersion extends AbstractIdEntity<Long> implements Serializable {

    @Column
    private String version;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column
    private Boolean current;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }
}
