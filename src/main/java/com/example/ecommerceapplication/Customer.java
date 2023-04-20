package com.example.ecommerceapplication;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class Customer {
    private int id;
    private String name, email, mobile, address;
    private TextField nameField, emailField, phoneField, addressField;
    GridPane accountPage;

    public Customer(int id, String name, String email, String mobile, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public GridPane customerDetails(){

        Text name = new Text("Your Name: ");
        Text email = new Text("Your Email address: ");
        Text phone = new Text("Your Phone Number: ");
        Text address = new Text("Your Address: ");

        nameField = new TextField(this.name);
        nameField.setEditable(false);
        emailField = new TextField(this.email);
        emailField.setEditable(false);
        phoneField = new TextField(this.mobile);
        phoneField.setEditable(false);
        addressField = new TextField(this.address);
        addressField.setEditable(false);

        accountPage = new GridPane();
        accountPage.setAlignment(Pos.CENTER);
        accountPage.setHgap(20);
        accountPage.setVgap(20);
        accountPage.add(name,0,0);
        accountPage.add(email,0,1);
        accountPage.add(phone,0,2);
        accountPage.add(address,0,3);
        accountPage.add(nameField,1,0);
        accountPage.add(emailField,1,1);
        accountPage.add(phoneField,1,2);
        accountPage.add(addressField,1,3);
        return accountPage;
    }

    public void editDetails(){
        nameField.setEditable(true);
        emailField.setEditable(true);
        phoneField.setEditable(true);
        addressField.setEditable(true);

    }

    public void updateCustomerDetails(){
        String updateCustomerQuery = "UPDATE customer SET name = '"+nameField.getText()+"', email = '"+emailField.getText()+"', mobile = '"+phoneField.getText()+"', address = '"+addressField.getText()+"' WHERE (cust_id = "+this.id+");";
        System.out.println(updateCustomerQuery);
        DbConnection dbConnection = new DbConnection();
        dbConnection.updateDatabase(updateCustomerQuery);
    }

    public void getUpdatedCustomerDetails(){
        String getQuery = "SELECT * FROM customer WHERE cust_id = "+this.id+";" ;
        System.out.println(getQuery);
        DbConnection connection = new DbConnection();
        ResultSet rs = connection.getQueryTable(getQuery);
        try {
            if(rs.next()){
                this.id = rs.getInt("cust_id");
                this.name = rs.getString("name");
                this.email = rs.getString("email");
                this.mobile = rs.getString("mobile");
                this.address = rs.getString("address");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
    public String getAddress() {
        return address;
    }
}
