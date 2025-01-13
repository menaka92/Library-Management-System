package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberDashboardFormController {
    public AnchorPane context;

    public void getBookOnAction(ActionEvent actionEvent) {
        try {
            setUi("ShowBooksForm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        try {
            setUi("LoginForm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
