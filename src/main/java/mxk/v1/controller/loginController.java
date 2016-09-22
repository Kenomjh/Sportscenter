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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mxk.v1.dao.loginDAO;
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

                mainController.mlm = lm;

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

    private void showMain() {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../main/resources/fxml/main.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle(":: Main ::");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/tlogofinal.png"));

        stage.show();


    }

    public void joinpg(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       logintxt.setFont(Font.loadFont( getClass().getResource("/font/rose.ttf").toExternalForm(), 15));
    }

    public void sendData(Button logoutbtn, Label username, Label nim,Stage stage) {
        this.logoutbtn = logoutbtn;
        this.username = username;
        this.nim = nim;
        this.stage = stage;
    }
}
