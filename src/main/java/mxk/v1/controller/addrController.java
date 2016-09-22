package mxk.v1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mxk.v1.dao.memberDAO;


public class addrController {
    @FXML TextField post;
    @FXML ListView postlist;
    private TextField addr1;
    private TextField addr2;
    private TextField addr3;
    private Stage stage;

    //private ObservableList<memberDAO> one = null;

    public void showpost(ActionEvent event) {
        postlist.setItems(memberDAO.showzipcode(post.getText()));
    } // showpost

    public void choice(ActionEvent event) {
        String addr[] = postlist.getSelectionModel().getSelectedItem().toString().split("[ ]");
        addr1.setText(addr[0].substring(0, 3));
        addr2.setText(addr[0].substring(4, 7));
        addr3.setText(addr[1]+" "+addr[2]+" "+addr[3]+" "+addr[4]+" "+addr[5]);
        stage.close();
    } // choice

    public void sendData(TextField addr1, TextField addr2, TextField addr3,Stage stage) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.stage = stage;
    }
} // class