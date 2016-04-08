package com.rambo.iw.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
@DatabaseTable(tableName = "lottery_result")
public class LotteryResult extends BaseEntity {

    @DatabaseField(columnName = "lottery_no")
    private String lotteryNo;

    @DatabaseField(columnName = "red_no1")
    private String RedNo1;

    @DatabaseField(columnName = "red_no2")
    private String RedNo2;

    @DatabaseField(columnName = "red_no3")
    private String RedNo3;

    @DatabaseField(columnName = "red_no4")
    private String RedNo4;

    @DatabaseField(columnName = "red_no5")
    private String RedNo5;

    @DatabaseField(columnName = "red_no6")
    private String RedNo6;

    @DatabaseField(columnName = "blue_no")
    private String blueNo;

    @DatabaseField(columnName = "open_time")
    private String openTime;

    @DatabaseField(columnName = "open_stamp")
    private String openStamp;

    public LotteryResult() {
    }

    public LotteryResult(String lotteryNo, String redNo1, String redNo2,
                         String redNo3, String redNo4, String redNo5, String redNo6,
                         String blueNo, String openTime, String openStamp) {
        super(lotteryNo,new Date());
        this.lotteryNo = lotteryNo;
        this.RedNo1 = redNo1;
        this.RedNo2 = redNo2;
        this.RedNo3 = redNo3;
        this.RedNo4 = redNo4;
        this.RedNo5 = redNo5;
        this.RedNo6 = redNo6;
        this.blueNo = blueNo;
        this.openTime = openTime;
        this.openStamp = openStamp;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getRedNo1() {
        return RedNo1;
    }

    public void setRedNo1(String redNo1) {
        RedNo1 = redNo1;
    }

    public String getRedNo2() {
        return RedNo2;
    }

    public void setRedNo2(String redNo2) {
        RedNo2 = redNo2;
    }

    public String getRedNo3() {
        return RedNo3;
    }

    public void setRedNo3(String redNo3) {
        RedNo3 = redNo3;
    }

    public String getRedNo4() {
        return RedNo4;
    }

    public void setRedNo4(String redNo4) {
        RedNo4 = redNo4;
    }

    public String getRedNo5() {
        return RedNo5;
    }

    public void setRedNo5(String redNo5) {
        RedNo5 = redNo5;
    }

    public String getRedNo6() {
        return RedNo6;
    }

    public void setRedNo6(String redNo6) {
        RedNo6 = redNo6;
    }

    public String getBlueNo() {
        return blueNo;
    }

    public void setBlueNo(String blueNo) {
        this.blueNo = blueNo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getOpenStamp() {
        return openStamp;
    }

    public void setOpenStamp(String openStamp) {
        this.openStamp = openStamp;
    }
}
