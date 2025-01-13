package model;

import javafx.scene.control.Button;

public class BookTm {
    private String name;
    private Button btn;

    public BookTm(String name, Button btn) {
        this.name = name;
        this.btn = btn;
    }

    public BookTm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
