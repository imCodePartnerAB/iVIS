package com.imcode.entities.oauth2;
import javax.persistence.*;
/**
 * Created by vitaly on 20.02.15.
 */
@Entity
@Table(name = "dbo_appTablesPermissions")
@IdClass(AppTableKey.class)
public class AppTablePermission {
//    public AppTablePermission(AppTableKey key) {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private Long application;

    @Column
    private String tableName;

    @Column
    private Integer permissions;
    //    })
//                    column = @Column(name="tableName"))
//            @AttributeOverride(name = "tableName",
//                    column = @Column(name="application")),
//            @AttributeOverride(name = "application",
//    @AttributeOverrides({
//    @Id
//
//    }
//        this.tableName = key.getTableName();
//        this.application = key.getApplication();
    public AppTablePermission() { }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplication() {
        return application;
    }

    public void setApplication(Long application) {
        this.application = application;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
