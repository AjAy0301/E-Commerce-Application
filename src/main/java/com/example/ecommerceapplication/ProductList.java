package com.example.ecommerceapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductList {
    private TableView<Product> productTable;
    public VBox createTable(ObservableList<Product> data){
        //column
        TableColumn id = new TableColumn("Product ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable = new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.backgroundProperty();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);
        return vBox;
    }

    public VBox getAllProducts(){
        ObservableList<Product> data = Product.getAllProducts();
        return createTable(data);
    }

    public Product getSelectedProduct(){
        return productTable.getSelectionModel().getSelectedItem();
    }
    public VBox getProductInCart(ObservableList<Product> data){
        return  createTable(data);
    }
    public VBox getSearchedProduct(String query){
        ObservableList<Product> data = Product.getSearchedProducts(query);
        return createTable(data);
    }

    public int getSubtotal(ObservableList<Product> data){
        int subtotal=0;
        for(Product product:data){
            subtotal+=product.getPrice();
        }
        return subtotal;
    }
}
