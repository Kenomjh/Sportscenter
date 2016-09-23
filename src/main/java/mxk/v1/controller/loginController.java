package mxk.v1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mxk.v1.dao.loginDAO;
import mxk.v1.model.loginMemberModel;
import mxk.v1.model.loginViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



/**
 * Created by java on 2016-09-19.
 */

public class loginController implements Initializable {

    @FXML private TextField userid;
    @FXML private PasswordField passwd;
    @FXML private Label logintxt;

    Stage stage;

    private Label username;
    private Label nim;
    private Button logoutbtn;
    private Button loginBtn;
    private Pane mainPane;

    public void loginpg(ActionEvent actionEvent) {

        String uid = userid.getText();
        String pwd = passwd.getText();
        int ino = loginDAO.idCheck(uid);


        if(ino==0){logintxt.setText("아이디를 다시 입력해주세요.");}
        else if(ino==1){logintxt.setText("비밀번호를 입력해주세요");
            int pno = loginDAO.pwdCheck(uid, pwd);
            if(pno==0){logintxt.setText("비밀번호를 확인해주세요.");}
            else if(ino==1){
                loginViewModel lm = loginDAO.createView(uid);
                loginMemberModel am = loginDAO.createlmm(uid);
                mainController.mlm = lm;
                mainController.mlmm = am;
                logintxt.setText("");
                stage.close();

                try {
                    username.setVisible(true);
                    nim.setVisible(true);
                    logoutbtn.setVisible(true);
                    username.setText(mainController.mlm.getUsername());
                }catch (Exception e){e.printStackTrace();}

            }//else if2
        }//else if1


    }

    public void joinpg(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/member.fxml"));
        Parent root = loader.load();

        stage.close();

        mainPane.getChildren().clear();
        mainPane.getChildren().add(root);

        memberController mc = loader.getController();
        mc.sendData(logoutbtn, loginBtn, username, nim, stage, mainPane);

        mc.showjoin();
    } // joinpg

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       logintxt.setFont(Font.loadFont( getClass().getResource("/font/rose.ttf").toExternalForm(), 15));
    }

    public void sendData(Button logoutbtn, Button loginBtn, Label username, Label nim,Stage stage, Pane mainPane) {
        this.logoutbtn = logoutbtn;
        this.loginBtn = loginBtn;
        this.username = username;
        this.nim = nim;
        this.stage = stage;
        this.mainPane = mainPane;
    } // sendData
}
