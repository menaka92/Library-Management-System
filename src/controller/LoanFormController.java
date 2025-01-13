package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BookTm;

import java.io.IOException;
import java.util.Optional;

public class LoanFormController {
    public AnchorPane context;
    public TableView tblBook;
    public TableColumn colId;
    public TableColumn colBook;

    public void initialize() {
        colBook.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void setCartItems(ObservableList<BookTm> selectedBooks) {
        tblBook.setItems(selectedBooks);
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }

    public void confrimOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"you successfully got the book/s", ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            try {
                setUi("LoginForm");
            }catch (Exception e){e.printStackTrace();}
        }
    }
}
