package mxk.v1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-19.
 */
public class kangseupController implements Initializable{
    @FXML   TabPane kangseupTab;
    @FXML    AnchorPane swim;
    @FXML    AnchorPane tennis;

    @FXML    AnchorPane step1;
    @FXML    AnchorPane step2;
    @FXML    AnchorPane step3;
    @FXML    Button nextBtn;

    @FXML public TextField userId;
    @FXML public TextField userName;
    @FXML public TextField addr;
    @FXML public TextField tel;

    @FXML public TextField userId2;
    @FXML public TextField userName2;
    @FXML public TextField addr2;
    @FXML public TextField tel2;



    private int checkAgree=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    //강습안내
    public void k0(){
        tennis.setVisible(false);
        swim.setVisible(true);
        SingleSelectionModel<Tab> sm = kangseupTab.getSelectionModel();
        sm.select(0);

    }

    public void k0_t(ActionEvent actionEvent) {
        tennis.setVisible(true);
        swim.setVisible(false);
        SingleSelectionModel<Tab> sm = kangseupTab.getSelectionModel();
        sm.select(0);
    }

    public void k1(){
        SingleSelectionModel<Tab> sm = kangseupTab.getSelectionModel();
        sm.select(1);
        step1.setVisible(true);
        step2.setVisible(false);
        step3.setVisible(false);
    }

    //강습신청
    public void showbtn(ActionEvent actionEvent) {
        if(checkAgree==1){
            nextBtn.setDisable(true);
            checkAgree=0;
        }else{nextBtn.setDisable(false);
            checkAgree=1;}
    }

    public void nextStep1(ActionEvent actionEvent) {
        step1.setVisible(false);
        step2.setVisible(true);
        step3.setVisible(false);

        userId.setText(mainController.mlm.getUserid());
        userName.setText(mainController.mlm.getUsername());
        addr.setText(mainController.mlm.getAddr());
        tel.setText(mainController.mlm.getTel());
    }

    public void nextStep2(ActionEvent actionEvent) {
        step1.setVisible(false);
        step2.setVisible(false);
        step3.setVisible(true);


        userId2.setText(mainController.mlm.getUserid());
        userName2.setText(mainController.mlm.getUsername());
        addr2.setText(mainController.mlm.getAddr());
        tel2.setText(mainController.mlm.getTel());
    }

    public void g2appliction(ActionEvent actionEvent) {
    }
}