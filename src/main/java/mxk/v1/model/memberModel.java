package mxk.v1.model;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class memberModel {
    private SimpleStringProperty mno;
    private SimpleStringProperty id;
    private SimpleStringProperty pw;
    private SimpleStringProperty name;
    private SimpleStringProperty bday;
    private SimpleStringProperty gender;
    private SimpleStringProperty addr1;
    private SimpleStringProperty addr2;
    private SimpleStringProperty addr3;
    private SimpleStringProperty addr4;
    private SimpleStringProperty tel;
    private SimpleStringProperty email;
    private SimpleStringProperty msg;
    private SimpleStringProperty ldate;

    public memberModel(String mno, String id, String pw, String name, String bday, String gender, String addr1, String addr2, String addr3, String addr4,
                       String tel, String email, String msg, String ldate) {
        this.mno = new SimpleStringProperty(mno);
        this.id = new SimpleStringProperty(id);
        this.pw = new SimpleStringProperty(pw);
        this.name = new SimpleStringProperty(name);
        this.bday = new SimpleStringProperty(bday);
        this.gender = new SimpleStringProperty(gender);
        this.addr1 = new SimpleStringProperty(addr1);
        this.addr2 = new SimpleStringProperty(addr2);
        this.addr3 = new SimpleStringProperty(addr3);
        this.addr4 = new SimpleStringProperty(addr4);
        this.tel = new SimpleStringProperty(tel);
        this.email = new SimpleStringProperty(email);
        this.msg = new SimpleStringProperty(msg);
        this.ldate = new SimpleStringProperty(ldate);
    }

    public String getMno() {
        return mno.get();
    }

    public void setMno(String mno) {
        this.mno.set(mno);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getPw() {
        return pw.get();
    }

    public void setPw(String pw) {
        this.pw.set(pw);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBday() {
        return bday.get();
    }

    public void setBday(String bday) {
        this.bday.set(bday);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAddr1() {
        return addr1.get();
    }

    public void setAddr1(String addr1) {
        this.addr1.set(addr1);
    }

    public String getAddr2() {
        return addr2.get();
    }

    public void setAddr2(String addr2) {
        this.addr2.set(addr2);
    }

    public String getAddr3() {
        return addr3.get();
    }

    public void setAddr3(String addr3) {
        this.addr3.set(addr3);
    }

    public String getAddr4() {
        return addr4.get();
    }

    public void setAddr4(String addr4) {
        this.addr4.set(addr4);
    }

    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getLdate() {
        return ldate.get();
    }

    public void setLdate(String ldate) {
        this.ldate.set(ldate);
    }

    public String getMsg() {
        return msg.get();
    }

    public void setMsg(String msg) {
        this.msg.set(msg);
    }
}
