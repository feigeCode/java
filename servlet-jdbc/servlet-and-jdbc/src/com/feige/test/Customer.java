package com.feige.test;


import java.sql.Date;

public class Customer {
    private String sno;
    private String sname;
    private String sex;
    private String class1;
    private Date sdate;
    private String tel;

    public Customer(){

    }
    public Customer(String sno, String sname, String sex, String class1, Date sdate, String tel) {
        this.sno = sno;
        this.sname = sname;
        this.sex = sex;
        this.class1 = class1;
        this.sdate = sdate;
        this.tel = tel;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString(){
        return "["+this.sno+","+this.sname+","+this.sex+","+this.class1+","+this.sdate+","+this.tel+"]";
    }
}
