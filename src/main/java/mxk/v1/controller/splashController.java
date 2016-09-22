package mxk.v1.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-13.
 */
public class splashController implements Initializable{

    @FXML ProgressBar pgBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pgBar.setStyle("-fx-accent: #0066FF;");
        Task<Void> t = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i=1;i<=100;i++){
                    updateProgress(i,100);
                    try {Thread.sleep(1);}
                    catch (Exception ex) {ex.printStackTrace();}
                }
                return null;
            }//call - UI 변경

            @Override
            protected void succeeded() {
                ((Stage)pgBar.getScene().getWindow()).close();
                showMainStage();        //메인창 표시
            }//succeeded - 작업 완료
        };

        pgBar.progressProperty().bind(
                t.progressProperty() );

        Thread task = new Thread(t);
        task.setDaemon(true);
        task.start();

    }//initialize

    private void showMainStage() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle(":: 울릉도민 체육관에 오신 것을 환영합니다!! ::");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/tlogofinal.png"));

        mainController mc = loader.getController();

        stage.setOnCloseRequest(event -> mc.closeApp(event));



        stage.show();

        //login stage
        FXMLLoader f2 = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root2 = null;
        try {
            root2 = f2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage a = new Stage();
        a.setScene(new Scene(root2));
        a.setTitle("로그인");
        a.initModality(Modality.WINDOW_MODAL);
        a.initStyle(StageStyle.UTILITY);
        a.initOwner(stage);

        a.setOnCloseRequest(event -> mc.closeApp2(event));

        a.show();

        loginController lc = f2.getController();
        lc.sendData(mc.getLogoutbtn(),mc.getUsername(),mc.getNim(),a);
    }
}//SplashController