package com.rambo.iw.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
@DatabaseTable(tableName = "ball")
public class Ball extends BaseEntity {
    public static final String RED_BALL = "_1";
    public static final String BLUD_BALL = "_2";
    @DatabaseField(columnName = "no")
    private String no;
    @DatabaseField(columnName = "open_count")
    private long openCount;
    @DatabaseField(columnName = "ball_type")
    private String ballType;

    public Ball(String no, long openCount, String ballType) {
        super(no + ballType, new Date());
        this.no = no;
        this.openCount = openCount;
        this.ballType = ballType;
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

    public String getBallType() {
        return ballType;
    }

    public void setBallType(String ballType) {
        this.ballType = ballType;
    }
}
