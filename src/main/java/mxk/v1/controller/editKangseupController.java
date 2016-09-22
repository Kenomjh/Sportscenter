package mxk.v1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mxk.v1.dao.kangseupDAO;

/**
 * Created by java on 2016-09-22.
 */
public class editKangseupController {
    @FXML public Pane editpane;
    @FXML public ComboBox selt1;
    @FXML public ComboBox selt2;
    @FXML public ComboBox selt3;
    @FXML public TextField payText;


    @FXML public TextField userId;
    @FXML public TextField userName;
    @FXML public TextField addr;
    @FXML public TextField tel;

    //콤보박스에 들어갈 아이템들을 저장할 변수
    private String[] kangseup = new String[2];
    private String[] program = new String[3];
    private String[] term = new String[3];
    private String apay;
    private String rsl;
    private String as;

    private  String rno;
    public int num ;
    private  String y1;
    private  String y2;
    private  String y3;
    private  String y4;


    public void startEditk(String rno, String y1, String y2, String y3, String y4) {
        this.rno = rno;
        selt1.setValue(y1);
        selt2.setValue(y2);
        selt3.setValue(y3);
        payText.setText(y4);

        kangseup[0] = "수영";
        kangseup[1] = "테니스";

        program[0] = "어린이반";
        program[1] = "성인반";
        program[2] = "직장인반";

        term[0] = "10월(1일~31일)";
        term[1] = "11월(1일~30일)";
        term[2] = "12월(1일~31일)";


        ObservableList k1 = FXCollections.observableArrayList(kangseup);
        ObservableList k2 = FXCollections.observableArrayList(program);
        ObservableList k3 = FXCollections.observableArrayList(term);

        selt1.setItems(k1);
        selt2.setItems(k2);
        selt3.setItems(k3);

        userId.setText(mainController.mlm.getUserid());
        userName.setText(mainController.mlm.getUsername());
        addr.setText(mainController.mlm.getAddr());
        tel.setText(mainController.mlm.getTel());


        selt2.setDisable(false);
        selt3.setDisable(false);
    }

    public void kStep1(Event event) {
        if (selt1.getSelectionModel().getSelectedItem() != null&&selt2.getSelectionModel().getSelectedItem() != null) {
            rsl = selt1.getSelectionModel().getSelectedItem() + "-" + selt2.getSelectionModel().getSelectedItem();
            kangseupDAO kd = new kangseupDAO();
            apay = String.valueOf(kd.payCk(rsl));
            payText.setText(apay);
        }
        as = selt3.getSelectionModel().getSelectedItem() + " ";

        System.out.println("a"+rno);
    }

    public void kStep2(Event event) {
        if (selt1.getSelectionModel().getSelectedItem() != null&&selt2.getSelectionModel().getSelectedItem() != null) {
            rsl = selt1.getSelectionModel().getSelectedItem() + "-" + selt2.getSelectionModel().getSelectedItem();
            kangseupDAO kd = new kangseupDAO();
            apay = String.valueOf(kd.payCk(rsl));
            payText.setText(apay);
        }
        as = selt3.getSelectionModel().getSelectedItem() + " ";
    }

    public void kStep3(Event event) {

    }

    public void update(ActionEvent ae) {
        kangseupDAO kd = new kangseupDAO();
        kd.updateKS(rsl,as, rno);
        Stage stage = (Stage)editpane.getScene().getWindow();
        stage.close();
    }

    public void delete(ActionEvent ae) {
        kangseupDAO kd = new kangseupDAO();
        kd.deleteKS(rno);
        Stage stage = (Stage)editpane.getScene().getWindow();
        stage.close();
    }



}
