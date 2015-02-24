package com.imcode.entities;

import javax.persistence.*;

/**
 * Created by vitaly on 20.02.15.
 */
@Entity
@Table(name = "dbo_appTablesPermissions")
@IdClass(AppTableKey.class)
public class AppTablePermission {
    public AppTablePermission() { }

    public AppTablePermission(AppTableKey key) {
        this.application = key.getApplication();
        this.tableName = key.getTableName();
    }

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "application",
                    column = @Column(name="application")),
            @AttributeOverride(name = "tableName",
                    column = @Column(name="tableName"))
    })

    private Long application;
    private String tableName;
    private Integer permissions;

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }
}
