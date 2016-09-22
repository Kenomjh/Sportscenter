package mxk.v1.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by java on 2016-09-22.
 */
public class pReservationModel {

    private SimpleStringProperty prno;
    private SimpleStringProperty pdate;
    private SimpleStringProperty ptime;
    private SimpleStringProperty ppay;
    private SimpleStringProperty tdate;
    private SimpleStringProperty ring;

    public pReservationModel(String prno, String pdate, String ptime, String ppay, String tdate, String ring) {
        this.prno = new SimpleStringProperty(prno);
        this.pdate = new SimpleStringProperty(pdate);
        this.ptime = new SimpleStringProperty(ptime);
        this.ppay = new SimpleStringProperty(ppay);
        this.tdate = new SimpleStringProperty(tdate);
        this.ring = new SimpleStringProperty(ring);
    }

    public String getPrno() {
        return prno.get();
    }

    public void setPrno(String prno) {
        this.prno.set(prno);
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

    public String getRing() {
        return ring.get();
    }

    public void setRing(String ring) {
        this.ring.set(ring);
    }
}
