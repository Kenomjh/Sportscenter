package mxk.v1.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by java on 2016-09-20.
 */
public class loginViewModel {
    private SimpleStringProperty mno;
    private SimpleStringProperty userid;
    private SimpleStringProperty username;
    private SimpleStringProperty addr;
    private SimpleStringProperty tel;

    public loginViewModel(String mno, String userid, String username, String addr, String tel) {
        this.mno = new SimpleStringProperty(mno);
        this.userid = new SimpleStringProperty(userid);
        this.username = new SimpleStringProperty(username);
        this.addr = new SimpleStringProperty(addr);
        this.tel =new SimpleStringProperty(tel);
    }

    public String getMno() {
        return mno.get();
    }

    public SimpleStringProperty mnoProperty() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno.set(mno);
    }

    public String getUserid() {
        return userid.get();
    }

    public SimpleStringProperty useridProperty() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getAddr() {
        return addr.get();
    }

    public SimpleStringProperty addrProperty() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr.set(addr);
    }

    public String getTel() {
        return tel.get();
    }

    public SimpleStringProperty telProperty() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }
}
