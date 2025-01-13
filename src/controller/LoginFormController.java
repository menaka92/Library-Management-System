package controller;

import db.DatabaseCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtEmail;
    public TextField txtPassword;
    public AnchorPane context;
    public ComboBox comRole;


    public void initialize() {

        String[] role = {"librarian", "member"};
        ObservableList<String> roleList = FXCollections.observableArrayList(role);
        comRole.setItems(roleList);
    }

    public void goToRegisterFormOnAction(ActionEvent actionEvent) {
        try {
            setUi("RegistrationForm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            User selectedUser = new DatabaseCode().login(txtEmail.getText(), (String) comRole.getValue());
            if (!(selectedUser == null)) {
                // login process
                if (selectedUser.getPassword().equals(txtPassword.getText())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Login as " + selectedUser.getRole(), ButtonType.OK).show();
                    if (comRole.getValue().equals("librarian")) {
                        System.out.println("hiiii");
                        setUi("LibrarianDashboardForm");
                    } else {
                        setUi("MemberDashboardForm");
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong Password", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User not found..", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
