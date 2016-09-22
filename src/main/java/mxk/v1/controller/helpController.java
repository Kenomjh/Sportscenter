package mxk.v1.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by java on 2016-09-20.
 */
public class helpController {
    public void g2h0() {

    }

    public void g2h1() {

    }

    public void g2h2() {
        Stage version = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));

        }catch (Exception e){e.printStackTrace();}
        version.setScene(new Scene(root));
        version.initStyle(StageStyle.UTILITY);
        version.setTitle("프로그램정보확인");
        version.showAndWait();

    }
}
