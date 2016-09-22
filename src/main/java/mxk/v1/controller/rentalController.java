package mxk.v1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mxk.v1.dao.rentalDAO;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-19.
 */
public class rentalController implements Initializable{

    @FXML TabPane rentalTab;
    @FXML Button nextbtn;
    @FXML Button nextbtn2;
    @FXML public AnchorPane apg;
    @FXML public AnchorPane rpg;
    @FXML public AnchorPane crpg;
    @FXML private TextField userid;
    @FXML private TextField username;
    @FXML private TextField addr;
    @FXML private TextField tel;
    @FXML private TextField ppay;
    @FXML private TextField ppay2;
    @FXML private TextField userid2;
    @FXML private TextField username2;
    @FXML private TextField addr2;
    @FXML private TextField tel2;
    @FXML private TextField limitdate;
    @FXML private Label uname;
    @FXML private DatePicker pdate;
    @FXML private DatePicker pdate2;
    @FXML private ComboBox ptime;
    @FXML private ComboBox ptime2;
    @FXML private Pane rentalPane;

    public int checkAgree;
    public int checkAgree2;
    // rs = 콤보박스로부터 대관시간을 저장할 변수


    private String[] ptimes = new String[9];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ptimes[0] = "06:00 - 08:00";
        ptimes[1] = "08:00 - 10:00";
        ptimes[2] = "10:00 - 12:00";
        ptimes[3] = "12:00 - 14:00";
        ptimes[4] = "14:00 - 16:00";
        ptimes[5] = "16:00 - 18:00";
        ptimes[6] = "18:00 - 20:00";
        ptimes[7] = "20:00 - 22:00";
        ptimes[8] = "22:00 - 24:00";

        ObservableList pt = FXCollections.observableArrayList(ptimes);
        ptime.setItems(pt);

    }

    public void r0() throws Exception{
        SingleSelectionModel<Tab> sm = rentalTab.getSelectionModel();
        sm.select(0);
    }

    public void r1() throws Exception{
        SingleSelectionModel<Tab> sm = rentalTab.getSelectionModel();
        sm.select(1);
    }

    public void showbtn(ActionEvent event) {
        if (checkAgree == 1) {
            nextbtn.setDisable(true);
            checkAgree = 0;
        } else {
            nextbtn.setDisable(false);
            checkAgree = 1;
        }
    }

    public void g2rpg(ActionEvent event) {
        apg.setVisible(false);
        rpg.setVisible(true);
        crpg.setVisible(false);

        userid.setText(mainController.mlm.getUserid());
        username.setText(mainController.mlm.getUsername());
        tel.setText(mainController.mlm.getTel());
        addr.setText(mainController.mlm.getAddr());
        uname.setText(mainController.mlm.getUsername());
    }

    public void showbtn2(ActionEvent event) {
        if (checkAgree2 == 1) {
            nextbtn2.setDisable(true);
            checkAgree2 = 0;
        } else {
            nextbtn2.setDisable(false);
            checkAgree2 = 1;
        }
    }

    public void g2crpg(ActionEvent event) {
        apg.setVisible(false);
        rpg.setVisible(false);
        crpg.setVisible(true);
        String rs = ptime.getSelectionModel().getSelectedItem()+"";
        String rs2 = pdate.getValue()+"";

        userid2.setText(mainController.mlm.getUserid());
        username2.setText(mainController.mlm.getUsername());
        addr2.setText(mainController.mlm.getAddr());
        tel2.setText(mainController.mlm.getTel());
        rentalDAO rd = new rentalDAO();
        //System.out.println(pdate.getValue());
        //pdate.setValue(LocalDate.of(1999,02,02));
        rd.newPrent(rs,rs2);

        Calendar c = Calendar.getInstance();
        String today =  c.get(Calendar.YEAR)+"-" + (c.get(Calendar.MONTH)+1)+"-"+  (c.get(Calendar.DATE)+1);

        ppay2.setText(ppay.getText());
        limitdate.setText(today);
        ptime2.setValue(ptime.getSelectionModel().getSelectedItem());
        pdate2.setValue(pdate.getValue());


    }

    public void outppay(ActionEvent ae) {
        String time = (String)ptime.getSelectionModel().getSelectedItem();
        rentalDAO rd = new rentalDAO();

        ppay.setText(rd.loadPpay(time));
    }

    public void g2m2s(ActionEvent ae) throws Exception{
        mainController mc = new mainController();
        mc.g2m2(rentalPane);
    }
}
