package mxk.v1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-09.
 */
public class centerController implements Initializable {

    @FXML TabPane centerTab;
    @FXML public AnchorPane swim;
    @FXML public AnchorPane tennis;
    @FXML public AnchorPane soccer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void c0() throws Exception{
        SingleSelectionModel<Tab> sm = centerTab.getSelectionModel();
        sm.select(0);
    }

    public void c1() throws Exception{
        SingleSelectionModel<Tab> sm = centerTab.getSelectionModel();
        sm.select(1);
    }

    public void c2() throws Exception{
        SingleSelectionModel<Tab> sm = centerTab.getSelectionModel();
        sm.select(2);
    }

    public void c3() throws Exception{
        SingleSelectionModel<Tab> sm = centerTab.getSelectionModel();
        sm.select(3);
    }

    public void g2tennis(ActionEvent event) {
        tennis.setVisible(true);
        soccer.setVisible(false);
        swim.setVisible(false);
    }

    public void g2soccer(ActionEvent event) {
        tennis.setVisible(false);
        soccer.setVisible(true);
        swim.setVisible(false);
    }

    public void g2swim(ActionEvent event) {
        tennis.setVisible(false);
        soccer.setVisible(false);
        swim.setVisible(true);
    }
}
