package com.imcode.entities.oauth2;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by vitaly on 20.02.15.
 */
@Embeddable
public class AppTableKey implements Serializable{

    private Long application;
    private String tableName;

    public AppTableKey() { }

    public AppTableKey(Long application, String tableName) {
        this.application = application;
        this.tableName = tableName;
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
