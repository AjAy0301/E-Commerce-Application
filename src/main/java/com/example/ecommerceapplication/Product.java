package com.example.ecommerceapplication;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty price;
    private SimpleStringProperty name;
    private int quantity;


    public Product(int id, int price, String name, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.price = new SimpleIntegerProperty(price);
        this.name = new SimpleStringProperty(name);
        this.quantity = quantity;
    }


    public static ObservableList<Product> getAllProducts(){
        String selectAllProductQuery = "SELECT id, price, name, quantity FROM product;";
        return fetchProductData(selectAllProductQuery);
    }


    public static ObservableList<Product> getSearchedProducts(String query){

        return fetchProductData(query);
    }

    public static ObservableList<Product> fetchProductData(String query){
        ObservableList<Product> data = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(query);
            while (rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getInt("price"), rs.getString("name"), rs.getInt("quantity"));
                data.add(product);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id.get();
    }

    public int getPrice() {
        return price.get();
    }

    public String getName() {
        return name.get();
    }

    public int getQuantity() {
        return quantity;
    }
}
