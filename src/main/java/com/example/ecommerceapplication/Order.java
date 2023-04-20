package com.example.ecommerceapplication;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Order {
    private SimpleStringProperty name;
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty quantity;
    private Date order_date;
    private SimpleStringProperty delivery_status;

    public Order(String name,int id, int quantity, Date order_date, String delivery_status){
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.order_date = order_date;
        this.delivery_status = new SimpleStringProperty(delivery_status);
    }

    public static ObservableList<Order> getAllOrders(int customerID){
        String selectOrdersQuery = "select product.name, orders.id, orders.quantity, order_date, delivery_status  from orders join product on product_id = product.id where customer_id="+customerID+";";
        return fetchOrderData(selectOrdersQuery);
    }

    public static ObservableList<Order> fetchOrderData(String query){
        ObservableList<Order> data = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(query);
            while (rs.next()){
                Order order = new Order(rs.getString("name"), rs.getInt("id"), rs.getInt("quantity"),rs.getDate("order_date"), rs.getString("delivery_status"));
                data.add(order);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean placeOrder(Customer customer, Product product){
        String groupOrderId = "SELECT max(group_order_id) +1 id FROM orders";
        DbConnection dbConnection = new DbConnection();
        try{
            ResultSet rs = dbConnection.getQueryTable(groupOrderId);
            if(rs.next()){
                String placeOrder = "INSERT INTO orders(group_order_id,customer_id,product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                return dbConnection.updateDatabase(placeOrder)!=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static int placeMultipleOrder(Customer customer, ObservableList<Product> productList){
        String groupOrderId = "SELECT max(group_order_id) +1 id FROM orders";
        DbConnection dbConnection = new DbConnection();
        try{
            ResultSet rs = dbConnection.getQueryTable(groupOrderId);
            int count =0;
            if(rs.next()){
                for(Product product:productList){
                    String placeOrder = "INSERT INTO orders(group_order_id,customer_id,product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                    count+=dbConnection.updateDatabase(placeOrder);
                }
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void cancelOrder(){
        String cancelOrderQuery = "UPDATE orders SET delivery_status = 'Cancelled' WHERE id = "+getId()+";";
        System.out.println(cancelOrderQuery);
        DbConnection dbConnection = new DbConnection();
        dbConnection.updateDatabase(cancelOrderQuery);
    }

    public String getName() {
        return name.get();
    }

    public int getId() {
        return id.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public Date getOrder_date() {
        return order_date;
    }

    public String getDelivery_status() {
        return delivery_status.get();
    }
}
