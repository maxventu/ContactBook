package com.itechart.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    final static Logger LOGGER =  LoggerFactory.getLogger("Connector DB");

    static {
        initConnector();
    }

    private static void initConnector() {
        try{
        InitialContext initContext= new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/ContactBook");
        }
        catch(NamingException e){
            LOGGER.error("Problem with context.xml",e);
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}