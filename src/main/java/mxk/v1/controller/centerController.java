package mxk.v1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

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
    @FXML public Label c1;
    @FXML public Label c2;
    @FXML public Label c3a;
    @FXML public Label c3b;
    @FXML public Label c3c;
    @FXML public Label c4;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        c1.setFont(Font.loadFont(getClass().getResource("/font/rose.ttf").toExternalForm(), 18));
        c2.setFont(Font.loadFont(getClass().getResource("/font/rose.ttf").toExternalForm(), 18));
        c3a.setFont(Font.loadFont(getClass().getResource("/font/rose.ttf").toExternalForm(), 18));
        c3b.setFont(Font.loadFont(getClass().getResource("/font/rose.ttf").toExternalForm(), 18));
        c3c.setFont(Font.loadFont(getClass().getResource("/font/rose.ttf").toExternalForm(), 18));
        c4.setFont(Font.loadFont(getClass().getResource("/font/rose.ttf").toExternalForm(), 18));
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
