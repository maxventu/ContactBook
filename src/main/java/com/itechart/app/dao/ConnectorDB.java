package com.itechart.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Maxim on 11/25/2015.
 */
public class ConnectorDB {
    private static DataSource dataSource;
    final static Logger LOGGER =  LoggerFactory.getLogger(ConnectorDB.class);

    static {
        initConnector();
    }

    private static void initConnector() {
        try{
        InitialContext initContext= new InitialContext();
            Context envContext  = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/mysql");
        }
        catch(NamingException e){
            LOGGER.error("Problem with context.xml",e);
        }

    }

    public static Connection getConnection() throws SQLException {
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){}
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/maximkalenik","mkalenik","mkalenik");*/
        return dataSource.getConnection();
    }
}