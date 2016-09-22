package mxk.v1.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by java on 2016-09-20.
 */
public class rentalModel {

    private SimpleStringProperty username;
    private SimpleStringProperty pdate;
    private SimpleStringProperty ptime;
    private SimpleStringProperty ppay;
    private SimpleStringProperty tdate;

    public rentalModel(String pdate, String ptime, String ppay,
                       String username, String tdate) {
        this.pdate = new SimpleStringProperty(pdate);
        this.ptime = new SimpleStringProperty(ptime);
        this.ppay = new SimpleStringProperty(ppay);
        this.username = new SimpleStringProperty(username);
        this.tdate = new SimpleStringProperty(tdate);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPdate() {
        return pdate.get();
    }

    public void setPdate(String pdate) {
        this.pdate.set(pdate);
    }

    public String getPtime() {
        return ptime.get();
    }

    public void setPtime(String ptime) {
        this.ptime.set(ptime);
    }

    public String getPpay() {
        return ppay.get();
    }

    public void setPpay(String ppay) {
        this.ppay.set(ppay);
    }

    public String getTdate() {
        return tdate.get();
    }

    public void setTdate(String tdate) {
        this.tdate.set(tdate);
    }
}
