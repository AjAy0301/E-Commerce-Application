package com.example.ecommerceapplication;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class OrderList {
    public TableView<Order> orderTable;
    public VBox createTable(ObservableList<Order> data){

        TableColumn name = new TableColumn("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn id = new TableColumn("Order ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn orderDate = new TableColumn("Order Date");
        orderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));

        TableColumn deliveryStatus = new TableColumn("Delivery Status");
        deliveryStatus.setCellValueFactory(new PropertyValueFactory<>("delivery_status"));



        orderTable = new TableView<>();
        orderTable.setItems(data);
        orderTable.getColumns().addAll(name,id,quantity,orderDate,deliveryStatus);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(orderTable);
        return vBox;
    }

    public VBox getAllOrders(int customerID){
        ObservableList<Order> data = Order.getAllOrders(customerID);
        return createTable(data);
    }

    public Order getSelectedOrder(){
        return orderTable.getSelectionModel().getSelectedItem();
    }
}
