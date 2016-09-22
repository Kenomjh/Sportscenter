package mxk.v1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mxk.v1.dao.kangseupDAO;
import mxk.v1.dao.memberDAO;
import mxk.v1.model.kangseupModel;
import mxk.v1.model.memberModel;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by java on 2016-09-19.
 */
public class kangseupController implements Initializable{
    @FXML public Pane aaaa;

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

    @FXML public ComboBox selt1;
    @FXML public ComboBox selt2;
    @FXML public ComboBox selt3;

    @FXML public ComboBox selt11;
    @FXML public ComboBox selt22;
    @FXML public ComboBox selt33;


    @FXML public TextField date1;
    @FXML public TextField payText;
    @FXML public TextField payText1;


    //임시 변수
    private int checkAgree=0;
    private String apay;
    private String rsl;
    private String as;


    //콤보박스에 들어갈 아이템들을 저장할 변수
    private String[] kangseup = new String[2];
    private String[] program = new String[3];
    private String[] term = new String[3];


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        kangseup[0] = "수영";
        kangseup[1] = "테니스";

        program[0] =  "어린이반";
        program[1] =  "성인반";
        program[2] =  "직장인반";

        term[0] =        "10월(1일~31일)";
        term[1] =        "11월(1일~30일)";
        term[2] =        "12월(1일~31일)";


        ObservableList k1 = FXCollections.observableArrayList(kangseup);
        ObservableList k2 = FXCollections.observableArrayList(program);
        ObservableList k3 = FXCollections.observableArrayList(term);

        selt1.setItems(k1);
        selt2.setItems(k2);
        selt3.setItems(k3);



    }

    //강습안내-수영
    public void k0(){
        tennis.setVisible(false);
        swim.setVisible(true);
        SingleSelectionModel<Tab> sm = kangseupTab.getSelectionModel();
        sm.select(0);

    }
    //강습안내-테니스
    public void k0_t(ActionEvent actionEvent) {
        tennis.setVisible(true);
        swim.setVisible(false);
        SingleSelectionModel<Tab> sm = kangseupTab.getSelectionModel();
        sm.select(0);
    }
    //강습안내-신청페이지로
    public void k1(){
        SingleSelectionModel<Tab> sm = kangseupTab.getSelectionModel();
        sm.select(1);
        step1.setVisible(true);
        step2.setVisible(false);
        step3.setVisible(false);

    }

    //강습신청-동의버튼활성화
    public void showbtn(ActionEvent actionEvent) {
        if(checkAgree==1){
            nextBtn.setDisable(true);
            checkAgree=0;
        }else{nextBtn.setDisable(false);
            checkAgree=1;}
    }
    //강습신청-동의버튼실행
    public void nextStep1(ActionEvent actionEvent) {
        step1.setVisible(false);
        step2.setVisible(true);
        step3.setVisible(false);

        userId.setText(mainController.mlm.getUserid());
        userName.setText(mainController.mlm.getUsername());
        addr.setText(mainController.mlm.getAddr());
        tel.setText(mainController.mlm.getTel());
    }



    //강습선택
    public void kStep1(Event event) {
        if(selt1.getSelectionModel().getSelectedItem()!=null){selt2.setDisable(false);}

    }
    public void kStep2(Event event) {
        if(selt2.getSelectionModel().getSelectedItem()!=null){
            selt3.setDisable(false);
            rsl = selt1.getSelectionModel().getSelectedItem()+"-"+selt2.getSelectionModel().getSelectedItem();
            kangseupDAO kd = new kangseupDAO();
            apay = String.valueOf( kd.payCk(rsl) );}

    }
    public void kStep3(Event event) {
        if(selt3.getSelectionModel().getSelectedItem()!=null){
            payText.setText(apay);
            as = selt3.getSelectionModel().getSelectedItem()+" ";}
    }
    //강습선택-신청버튼
    public void nextStep2(ActionEvent actionEvent) {
        step1.setVisible(false);
        step2.setVisible(false);
        step3.setVisible(true);

        //값 고정하기
        Calendar c = Calendar.getInstance();
        String today =  c.get(Calendar.YEAR)+"-" + (c.get(Calendar.MONTH)+1)+"-"+  (c.get(Calendar.DATE)+1);

        userId2.setText(userId.getText());
        userName2.setText(userName.getText());
        addr2.setText(addr.getText());
        tel2.setText(tel.getText());
        payText1.setText(payText.getText());
        date1.setText(today);
        selt11.setValue(selt1.getSelectionModel().getSelectedItem());
        selt22.setValue(selt2.getSelectionModel().getSelectedItem());
        selt33.setValue(selt3.getSelectionModel().getSelectedItem());



        kangseupDAO kd = new kangseupDAO();
        kd.kangseupLoad(rsl,as);



    }

    //신청완료-메인페이지로
    public void g2appliction(ActionEvent actionEvent) throws Exception{
        mainController mc = new mainController();
        mc.g2m2(aaaa);
    }

}