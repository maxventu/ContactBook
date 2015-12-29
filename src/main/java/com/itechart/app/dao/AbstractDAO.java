package com.itechart.app.dao;

import com.itechart.app.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/1/2015.
 */
public abstract class AbstractDAO  <K,T extends Entity>{
    protected static final Logger LOGGER = LoggerFactory.getLogger("DAO");

    protected abstract PreparedStatement prepareStatementFindAll(Connection connection) throws SQLException;
    protected abstract PreparedStatement prepareStatementFindEntityById(Connection connection,K id) throws SQLException;
    protected abstract PreparedStatement prepareStatementDelete(Connection connection, K id) throws SQLException;
    protected abstract PreparedStatement prepareStatementCreate(Connection connection, T entity) throws SQLException;
    protected abstract PreparedStatement prepareStatementUpdate(Connection connection,T entity) throws SQLException;
    protected abstract PreparedStatement prepareStatementMaxRow(Connection connection)throws SQLException;

    protected abstract T readEntityFrom(ResultSet entityResultSet) throws SQLException;
    protected abstract K readKeyFrom(ResultSet entityResultSet)throws SQLException;

    public ArrayList<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<T> arrayList = new ArrayList<T>();
        try {
            connection = ConnectorDB.getConnection();
            statement = prepareStatementFindAll(connection);
            ResultSet addressResultSet = statement.executeQuery();
            while (addressResultSet.next()) {
                arrayList.add(readEntityFrom(addressResultSet));
            }
            //if(arrayList.isEmpty())arrayList.add(new T());
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding all", exception);
        }
        finally {
            closeConnection(connection);
        }
        return arrayList;
    }
    public T findEntityById(K id){
        Connection connection = null;
        PreparedStatement statement = null;
        T entity = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = prepareStatementFindEntityById(connection,id);
            ResultSet attachmentResultSet = statement.executeQuery();
            while (attachmentResultSet.next()){
                entity = readEntityFrom(attachmentResultSet);
            }
        }
        catch (Exception ex){
            LOGGER.error("something went wrong while finding entity", ex);
        }
        finally {
            closeConnection(connection);
        }
        return entity;
    }
    public void delete(K id){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = prepareStatementDelete(connection, id);
            LOGGER.debug("executing delete query {}", statement);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting entity", ex);
        }
        finally {
            closeConnection(connection);
        }
    }
    public void create(T entity){
        LOGGER.debug("creating entity");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            LOGGER.debug("start creating entity");
            connection = ConnectorDB.getConnection();
            statement = prepareStatementCreate(connection, entity);
            LOGGER.debug("executing create query {}", statement);
            statement.executeUpdate();
            LOGGER.debug("query executed");
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating entity", ex);
        }
        catch (Exception e){
            LOGGER.error("Unable to init creation query", e);
        }
        finally {
            closeConnection(connection);
        }
    }
    public void update(T entity){

        Connection connection = null;
        try{
            connection = ConnectorDB.getConnection();
            PreparedStatement statement = prepareStatementUpdate(connection,entity);
            LOGGER.debug("executing update query {}", statement);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong while updating entity", ex);
        }
        finally {
            closeConnection(connection);
        }
    }

    public K maxRow(){
        Connection connection = null;
        PreparedStatement statement = null;
        K maxRow = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = prepareStatementMaxRow(connection);
            LOGGER.debug("executing query {}", statement);
            ResultSet attachmentResultSet = statement.executeQuery();
            while (attachmentResultSet.next()){
                maxRow = readKeyFrom(attachmentResultSet);
                LOGGER.debug("maxRow is {}", maxRow);
            }
        }
        catch (Exception ex){
            LOGGER.error("something went wrong while finding entity", ex);
        }
        finally {
            closeConnection(connection);
        }
        return maxRow;
    }

    protected void closeConnection(Connection connection){
        try{
            if(connection!=null)
                connection.close();
        }
        catch (Exception e){
            LOGGER.error("connection wasn't closed", e);
        }
    }
}
