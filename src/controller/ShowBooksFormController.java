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
import model.BookTm;
import org.w3c.dom.css.CSSRule;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowBooksFormController {
    public AnchorPane context;
    public TableView btlBook;
    public TableColumn colBookName;
    public TableColumn colOption;
    public ComboBox comCategory;
    private final ObservableList<BookTm> selectedBooks = FXCollections.observableArrayList();
    List<String> list = new ArrayList<>();
    public void initialize() {
        //meth1();
        loadColumns();
        loadComboBox();
        comCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedValue = newValue.toString();
                loadTbale(selectedValue);
            }
        });
    }

    void methForUpdateBookQty(){
        for (BookTm tm : selectedBooks){
            list.add(tm.getName());
            String name = tm.getName();// capture the book name
            try {
                PreparedStatement pstm1 = DBConnection.getInstance().getConnection().prepareStatement("SELECT id,qty FROM book WHERE name=?");
                pstm1.setString(1,name);
                ResultSet query1Set = pstm1.executeQuery();

                while (query1Set.next()){
                    int qty = query1Set.getInt("qty");
                    int id = query1Set.getInt("id");

                    PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE book SET qty=? WHERE name=? AND id=?");
                    pstm.setInt(1,qty=qty-1);
                    pstm.setString(2,name);
                    pstm.setInt(3,id);
                    pstm.executeUpdate();
                }
                //update issued date and 

            }catch (Exception e){e.printStackTrace();}
        }
        System.out.println(list);
    }

    private void loadColumns() {
        colBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
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

    private void loadTbale(String selectedValue) {
        try {
            ObservableList<BookTm> obList = FXCollections.observableArrayList();
            String sql = "SELECT  b.name FROM book b INNER JOIN category c ON b.category_id = c.id WHERE c.name = ?";
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, selectedValue);
            ResultSet set = pstm.executeQuery();

            while (set.next()) {
                Button btn = new Button("Get");
                BookTm book = new BookTm(set.getString(1), btn);
                obList.add(book);
                btn.setOnAction(e -> {
                    if (btn.getText().equals("Get")) {
                        //logic
                        selectedBooks.add(book);
                        btn.setText("Remove");
                    } else {
                        //logic
                        selectedBooks.remove(book);
                        btn.setText("Get");
                    }
                    btlBook.refresh();
                });
            }
            btlBook.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void viewCartOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CartForm.fxml"));
            AnchorPane cartRoot = loader.load();

            // Pass the selected books to the CartFormController
            CartFormController cartController = loader.getController();
            cartController.setCartItems(selectedBooks);

            // Display the popup
            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(cartRoot));
            popupStage.setTitle("Cart");
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void brrowBooksOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoanForm.fxml"));
            AnchorPane loanFormRoot = loader.load();

            LoanFormController loanFormController = loader.getController();
            loanFormController.setCartItems(selectedBooks);
            methForUpdateBookQty();

            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(loanFormRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void backOnAction(ActionEvent actionEvent) {
        try {
            setUi("MemberDashboardForm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}