package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.UserTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShowMembersFormController {
    public AnchorPane context;
    public TableView tblMember;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colOption;
    public TextField txtSearch;

    public void initialize() {
        loadTable("");
        loadColumns();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadTable(newValue);
        });
    }

    private void loadColumns() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    ObservableList<UserTm> obList = FXCollections.observableArrayList();
    private void loadTable(String searchText) {
        obList.clear();//
        searchText = "%" + searchText.toLowerCase() + "%";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT id, name FROM user WHERE role='member' AND LOWER(name) LIKE ?");
            pstm.setString(1, searchText);
            ResultSet set = pstm.executeQuery();

            while (set.next()) {
                Button btnDelete = new Button("Delete");
                int userId = set.getInt("id");
                UserTm user = new UserTm(userId, set.getString("name"), btnDelete);
                String finalSerchText = searchText;
                btnDelete.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this member?", ButtonType.OK, ButtonType.CANCEL);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        try {
                            PreparedStatement deletePstm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM user WHERE id=?");
                            deletePstm.setInt(1, userId);
                            int isDeleted = deletePstm.executeUpdate();
                            if (isDeleted > 0) {
                                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully.").show();
                                loadTable(finalSerchText); // Refresh the table after deletion
                            } else {
                                new Alert(Alert.AlertType.WARNING, "User deletion failed. Please try again.").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                            new Alert(Alert.AlertType.ERROR, "Cannot delete user. Check for dependent records.").show();
                        }
                    }
                });

                obList.add(user);
            }
            tblMember.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void backOnAction(ActionEvent actionEvent) {
        try {
            setUi("LibrarianDashboardForm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}