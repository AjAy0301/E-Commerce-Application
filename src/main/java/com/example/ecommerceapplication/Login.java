package com.example.ecommerceapplication;

import java.sql.ResultSet;

public class Login {
    public Customer customerLogin(String username, String password){
        String loginQuery = "SELECT * FROM customer WHERE (email = '"+username+"' OR mobile = '"+username+"' ) AND password = '"+password+"';";
        DbConnection connection = new DbConnection();
        ResultSet rs = connection.getQueryTable(loginQuery);
        try {
            if(rs.next()){
                return new Customer(rs.getInt("cust_id"), rs.getString("name"),
                         rs.getString("email"),rs.getString("mobile"),rs.getString("address"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
