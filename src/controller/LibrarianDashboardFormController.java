package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LibrarianDashboardFormController {
    public AnchorPane context;

    public void getBookOnAction(ActionEvent actionEvent) {
        try {
            setUi("ShowMembersForm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        try {
            setUi("LoginForm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void bookStorageOnAction(ActionEvent actionEvent) {
        try {
            setUi("ShowBookQtyForm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
