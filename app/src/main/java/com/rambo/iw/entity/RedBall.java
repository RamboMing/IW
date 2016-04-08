package com.rambo.iw.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
@DatabaseTable(tableName = "red_ball")
public class RedBall extends BaseEntity {
    @DatabaseField(columnName = "no")
    private String no;
    @DatabaseField(columnName = "open_count")
    private long openCount;

    public RedBall(String no, long openCount) {
        super(no, new Date());
        this.no = no;
        this.openCount = openCount;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public long getOpenCount() {
        return openCount;
    }

    public void setOpenCount(long openCount) {
        this.openCount = openCount;
    }
}
