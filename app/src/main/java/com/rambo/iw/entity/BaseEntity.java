package com.rambo.iw.entity;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class BaseEntity extends TableEntity {
    @DatabaseField(columnName = "id")
    protected String id;
    @DatabaseField(columnName = "modifydate")
    private Date modifydate;// //修改后及时更新

    public BaseEntity() {
    }

    public BaseEntity(String id, Date modifydate) {
        this.id = id;
        this.modifydate = modifydate;
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}
