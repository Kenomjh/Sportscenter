package mxk.v1.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by java on 2016-09-22.
 */
public class kReservationModel {
    private SimpleStringProperty rno;
    private SimpleStringProperty lesson;
    private SimpleStringProperty term;
    private SimpleStringProperty lpay;
    private SimpleStringProperty tday;
    private SimpleStringProperty ing;

    public kReservationModel(String rno, String lesson, String term, String lpay, String tday, String ing) {
        this.rno = new SimpleStringProperty(rno);
        this.lesson = new SimpleStringProperty(lesson);
        this.term =new SimpleStringProperty( term);
        this.lpay =new SimpleStringProperty( lpay);
        this.tday =new SimpleStringProperty( tday);
        this.ing =new SimpleStringProperty( ing);
    }

    public String getRno() {
        return rno.get();
    }

    public SimpleStringProperty rnoProperty() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno.set(rno);
    }

    public String getLesson() {
        return lesson.get();
    }

    public SimpleStringProperty lessonProperty() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson.set(lesson);
    }

    public String getTerm() {
        return term.get();
    }

    public SimpleStringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public String getLpay() {
        return lpay.get();
    }

    public SimpleStringProperty lpayProperty() {
        return lpay;
    }

    public void setLpay(String lpay) {
        this.lpay.set(lpay);
    }

    public String getTday() {
        return tday.get();
    }

    public SimpleStringProperty tdayProperty() {
        return tday;
    }

    public void setTday(String tday) {
        this.tday.set(tday);
    }

    public String getIng() {
        return ing.get();
    }

    public SimpleStringProperty ingProperty() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing.set(ing);
    }
}