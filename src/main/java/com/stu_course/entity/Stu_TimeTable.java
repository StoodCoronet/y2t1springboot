package com.stu_course.entity;

public class Stu_TimeTable {
    private String xqj;//星期几
    private String start;//开始时间；第几节课
    private String ks; //课时。总共几个课时
    private String info;//上课信息（老师，教室等等）

    public String getXqj() {
        return xqj;
    }

    public void setXqj(String xqj) {
        this.xqj = xqj;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
