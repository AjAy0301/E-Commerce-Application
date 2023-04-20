package com.example.ecommerceapplication;

import java.sql.*;

public class DbConnection {
    private static final String url = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String username = "root";
    private static final String password = "ajayajay";
    private Statement getStatement(){
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            return connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getQueryTable(String query){
        try {
            Statement statement = getStatement();
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateDatabase(String query){
        try {
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DbConnection connection = new DbConnection();
        ResultSet rs = connection.getQueryTable("select * from customer");
        if(rs!=null){
            System.out.println("connection successful");
        }else{
            System.out.println("Connection failed");
        }
    }
}
