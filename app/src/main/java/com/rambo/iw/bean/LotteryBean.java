package com.rambo.iw.bean;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class LotteryBean {


    private String expect;
    private String opencode;
    private String opentime;
    private String opentimestamp;

    public LotteryBean(String expect, String opencode, String opentime, String opentimestamp) {
        this.expect = expect;
        this.opencode = opencode;
        this.opentime = opentime;
        this.opentimestamp = opentimestamp;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getOpencode() {
        return opencode;
    }

    public void setOpencode(String opencode) {
        this.opencode = opencode;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getOpentimestamp() {
        return opentimestamp;
    }

    public void setOpentimestamp(String opentimestamp) {
        this.opentimestamp = opentimestamp;
    }

    @Override
    public String toString() {
        return "LotteryBean{" +
                "expect='" + expect + '\'' +
                ", opencode='" + opencode + '\'' +
                ", opentime='" + opentime + '\'' +
                ", opentimestamp='" + opentimestamp + '\'' +
                '}';
    }
}
