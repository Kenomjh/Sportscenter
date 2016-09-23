package mxk.v1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mxk.v1.dao.memberDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-21.
 */
public class outController implements Initializable{
    @FXML TextField outid;
    @FXML PasswordField outpw;
    @FXML TextArea outtxt;

    @FXML AnchorPane outPane;

    private Stage stage;
    private Pane mainPane;
    private Button logoutbtn; // **
    private Button loginBtn;
    private Label username;
    private Label nim;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        outid.setText(mainController.mlmm.getUserid());
    } // initialize **

    public void outdb(ActionEvent event) throws Exception {
        if (!outpw.getText().equals(mainController.mlmm.getPasswd())) {
            showWarn("비밀번호가 일치하지 않습니다!!");
        } else {
            memberDAO.deletedb(outid.getText());
            showOk("탈퇴가 완료되었습니다.");
            stage = (Stage)outPane.getScene().getWindow();
            stage.close();

            FXMLLoader loader2= new FXMLLoader(getClass().getResource("/fxml/subMain.fxml"));
            Parent root2 = loader2.load();

            System.out.println( "oc :" + mainPane );

            mainPane.getChildren().clear();
            mainPane.getChildren().add(root2);

            username.setVisible(false);
            nim.setVisible(false);
            logoutbtn.setVisible(false);
            loginBtn.setVisible(false);

            //login stage
            FXMLLoader f2 = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root3 = null;
            try {
                root3 = f2.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage a = new Stage();
            a.setScene(new Scene(root3));
            a.setTitle("로그인");
            a.initModality(Modality.WINDOW_MODAL);
            a.initStyle(StageStyle.UTILITY);
            a.initOwner(stage);

            mainController mc = new mainController();

            a.setOnCloseRequest(windowEventevent -> mc.closeApp2(event));

            a.show();
            Pane newP = mc.cllpane();
            loginController lc = f2.getController();
            lc.sendData(mc.getLogoutbtn(),null,mc.getUsername(),mc.getNim(),a,newP);



        } // if-else
    } // outdb **

    private void showWarn(String s) {
        Alert warn = new Alert(Alert.AlertType.WARNING);
        warn.setTitle("오류!!");
        warn.setHeaderText(null);
        warn.setContentText(s);
        warn.showAndWait();
    } // showWarn **

    private void showOk(String s) {
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("성공!!");
        warn.setHeaderText(null);
        warn.setContentText(s);
        warn.showAndWait();
    } // showWarn **

    public void sendData(Button logoutbtn, Button loginBtn, Label username, Label nim,Stage stage, Pane mainPane) {
        this.logoutbtn = logoutbtn;
        this.loginBtn = loginBtn;
        this.username = username;
        this.nim = nim;
        this.stage = stage;
        this.mainPane = mainPane;
    } // sendData  ** -> memberController의 out으로
} // class