package mxk.v1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mxk.v1.dao.rentalDAO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-22.
 */
public class editRController implements Initializable {

    @FXML private TextField userid;
    @FXML private TextField username;
    @FXML private TextField addr;
    @FXML private TextField tel;
    @FXML private ComboBox ptime;
    @FXML private TextField ppay;
    @FXML private DatePicker pdate;
    @FXML private Pane editpane;

    private String[] ptimes = new String[9];
    private static String prno;

    public String rs;
    public String rs2;

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

        userid.setText(mainController.mlm.getUserid());
        username.setText(mainController.mlm.getUsername());
        tel.setText(mainController.mlm.getTel());
        addr.setText(mainController.mlm.getAddr());
    }

    public void outppay(ActionEvent event) {

        this.rs = (String)ptime.getSelectionModel().getSelectedItem();
        rentalDAO rd = new rentalDAO();

        ppay.setText(rd.loadPpay(rs));
    }

    public void updateRent(ActionEvent event) {
        rs2 = pdate.getValue()+"";

        rentalDAO rd = new rentalDAO();
        if (rs != null && rs2 != null && prno != null) {
            rd.updateRS(rs, rs2, prno);
            Stage stage = (Stage)editpane.getScene().getWindow();
            stage.close();
        } else {
            Alert warn = new Alert(Alert.AlertType.WARNING);
            warn.setTitle(":: 예약 정보 입력오류 ::");
            warn.setHeaderText(null);
            warn.setContentText("예약일정을 선택해주세요!!");
            warn.showAndWait();
        }

    }

    public void deleteRent(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
        info.setTitle("삭제");
        info.setHeaderText(null);
        info.setContentText("예약하신 정보를 삭");

        rentalDAO rd = new rentalDAO();
        rd.deleteRS(prno);

        Stage stage = (Stage)editpane.getScene().getWindow();
        stage.close();
    }

    public void sendData(String prno) {
        this.prno = prno;
    }
}
