package com.itechart.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Maxim on 11/25/2015.
 */
public class ConnectorDB {
    private static Connection connection;

    public static void getConnection() throws SQLException {
        closeConnection();
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        connection = DriverManager.getConnection(url, user, pass);
    }

    public static void closeConnection() throws SQLException{
        if(connection!=null)connection.close();
    }
}