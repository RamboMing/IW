package com.rambo.iw.bean;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class JsonBean {
    private String code;
    private String data;
    private String rows;
    private String info;

    public JsonBean(String code, String data, String rows, String info) {
        this.code = code;
        this.data = data;
        this.rows = rows;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "code='" + code + '\'' +
                ", data='" + data + '\'' +
                ", rows='" + rows + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
