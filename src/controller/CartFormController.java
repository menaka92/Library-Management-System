package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BookTm;

public class CartFormController {
    public TableView<BookTm> tblCart;
    public TableColumn<BookTm, String> colCartBookName;

    public void initialize() {
        colCartBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void setCartItems(ObservableList<BookTm> cartItems) {
        tblCart.setItems(cartItems);
    }
}
