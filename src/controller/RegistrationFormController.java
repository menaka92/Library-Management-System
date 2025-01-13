package controller;

import db.DatabaseCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class RegistrationFormController {
    public AnchorPane context;
    public RadioButton rbtnLibrian;
    public ToggleGroup person;
    public RadioButton rbtnMember;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPassword;


    public void goToLoginFormOnAction(ActionEvent actionEvent) {
        try {
            setUi("LoginForm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrationOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (rbtnLibrian.isSelected()) {
            String role = rbtnLibrian.getText();
            String genID1 = UUID.randomUUID().toString().substring(0, 10);
            User user = new User(genID1, txtName.getText(), txtEmail.getText(), txtPassword.getText(),role);
            boolean isSaved = new DatabaseCode().userRegistation(user);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Librarian saved..").show();
                return;
            }else {
                new Alert(Alert.AlertType.ERROR, "try again..").show();
            }
        }
        if (rbtnMember.isSelected()) {
            String role = rbtnMember.getText();
            String genID1 = UUID.randomUUID().toString().substring(0, 10);
            User user = new User(genID1, txtName.getText(), txtEmail.getText(), txtPassword.getText(),role);
            boolean isSaved = new DatabaseCode().userRegistation(user);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Member saved..").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "try again..").show();
            }
        }

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
