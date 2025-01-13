package model;

import javafx.scene.control.Button;

public class BookQtyTm {
    private String name;
    private int qty;

    public BookQtyTm() {
    }

    public BookQtyTm(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
