package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BookQtyTm;
import model.BookTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShowBookQtyFormController {
    public AnchorPane context;
    public ComboBox comCategory;
    public TableView tblBook;
    public TableColumn colBookName;
    public TableColumn colQty;

    public void initialize() {
        loadColumns();
        loadComboBox();
        comCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedValue = newValue.toString();
                loadTbale(selectedValue);
            }
        });
    }

    private void loadColumns() {
        colBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadTbale(String selectedValue) {
        try {
            ObservableList<BookQtyTm> obList = FXCollections.observableArrayList();
            String sql = " SELECT book.name, book.qty FROM book INNER JOIN category ON book.category_id = category.id WHERE category.name = ?";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, selectedValue);
            ResultSet set = pstm.executeQuery();
            while (set.next()) {
                BookQtyTm bookQty = new BookQtyTm(set.getString("book.name"),set.getInt("book.qty"));
                obList.add(bookQty);
            }
            tblBook.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadComboBox() {
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT name FROM category");
            ResultSet set = pstm.executeQuery();
            while (set.next()) {
                comCategory.getItems().add(set.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) {
        try {
            setUi("LibrarianDashboardForm");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
