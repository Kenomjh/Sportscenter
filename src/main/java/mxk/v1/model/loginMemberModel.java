package mxk.v1.model;

import javafx.beans.property.SimpleStringProperty;


/**
 * Created by java on 2016-09-20.
 */
public class loginMemberModel {
    // member
    private SimpleStringProperty mno;
    private SimpleStringProperty userid;
    private SimpleStringProperty passwd;
    private SimpleStringProperty username;
    private SimpleStringProperty bday;
    private SimpleStringProperty gender;
    private SimpleStringProperty addr;
    private SimpleStringProperty tel;
    private SimpleStringProperty email;
    private SimpleStringProperty ldate;

    public loginMemberModel(String mno, String userid, String passwd, String username, String bday, String gender, String addr, String tel, String email, String ldate) {
        this.mno = new SimpleStringProperty(mno);
        this.userid = new SimpleStringProperty(userid);
        this.passwd = new SimpleStringProperty(passwd);
        this.username = new SimpleStringProperty(username);
        this.bday = new SimpleStringProperty(bday);
        this.gender = new SimpleStringProperty(gender);
        this.addr = new SimpleStringProperty(addr);
        this.tel =new SimpleStringProperty( tel);
        this.email =new SimpleStringProperty(email);
        this.ldate =new SimpleStringProperty( ldate);
    } // loginMemberModel

    // **
    public String getMno() {
        return mno.get();
    }

    public void setMno(String mno) {
        this.mno.set(mno);
    }

    public String getUserid() {
        return userid.get();
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
    }

    public String getPasswd() {
        return passwd.get();
    }

    public void setPasswd(String passwd) {
        this.passwd.set(passwd);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
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

    public String getAddr() {
        return addr.get();
    }

    public void setAddr(String addr) {
        this.addr.set(addr);
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
} // class
