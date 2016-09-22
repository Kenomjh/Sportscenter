package mxk.v1.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by java on 2016-09-20.
 */
public class kangseupModel {

    private SimpleStringProperty userid;
    private SimpleStringProperty userName;
    private SimpleStringProperty userAddress;
    private SimpleStringProperty userPN;
    private SimpleStringProperty slctK;
    private SimpleStringProperty slctP;
    private SimpleStringProperty slctT;
    private SimpleStringProperty day1;
    private SimpleIntegerProperty pay;

    public kangseupModel(String userid, String userName, String userAddress, String userPN, String slctK, String slctP, String slctT, String day1, int pay) {
        this.userid = new SimpleStringProperty(userid);
        this.userName = new SimpleStringProperty(userName);
        this.userAddress = new SimpleStringProperty(userAddress);
        this.userPN = new SimpleStringProperty(userPN);
        this.slctK = new SimpleStringProperty(slctK);
        this.slctP = new SimpleStringProperty(slctP);
        this.slctT = new SimpleStringProperty(slctT);
        this.day1 = new SimpleStringProperty(day1);
        this.pay = new SimpleIntegerProperty(pay);
    }

    public String getUserid() {
        return userid.get();
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserAddress() {
        return userAddress.get();
    }

    public void setUserAddress(String userAddress) {
        this.userAddress.set(userAddress);
    }

    public String getUserPN() {
        return userPN.get();
    }

   public void setUserPN(String userPN) {
        this.userPN.set(userPN);
    }

    public String getSlctK() {
        return slctK.get();
    }

   public void setSlctK(String slctK) {
        this.slctK.set(slctK);
    }

    public String getSlctP() {
        return slctP.get();
    }

    public void setSlctP(String slctP) {
        this.slctP.set(slctP);
    }

    public String getSlctT() {
        return slctT.get();
    }

     public void setSlctT(String slctT) {
        this.slctT.set(slctT);
    }

    public String getDay1() {
        return day1.get();
    }

    public void setDay1(String day1) {
        this.day1.set(day1);
    }

    public int getPay() {
        return pay.get();
    }

    public void setPay(int pay) {
        this.pay.set(pay);
    }


}
