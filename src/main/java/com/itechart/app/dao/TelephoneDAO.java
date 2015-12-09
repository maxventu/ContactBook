package com.itechart.app.dao;

import com.itechart.app.entity.Telephone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/8/2015.
 */

public class TelephoneDAO extends AbstractDAO<Integer,Telephone>{
    private static final String TELEPHONE_FIND_ALL_BY_CONTACT_ID_QUERY =
            "SELECT id,country_code,number,type,comment,contact_id  FROM telephone WHERE contact_id = ? AND is_deleted=0;";
    private static final String TELEPHONE_FIND_ALL_QUERY =
            "SELECT id,country_code,number,type,comment,contact_id  FROM telephone WHERE is_deleted=0;";
    private static final String TELEPHONE_SELECT_QUERY =
            "SELECT id,country_code,number,type,comment,contact_id  FROM telephone WHERE id = ? AND is_deleted=0;";
    private static final String TELEPHONE_DELETE_QUERY =
            "UPDATE telephone SET is_deleted=1, date_deleted=NOW() WHERE id=?;";
    private static final String TELEPHONE_CREATE_QUERY =
            "INSERT INTO telephone (country_code,number,type,comment,contact_id) VALUES (?, ?, ?, ?);";
    private static final String TELEPHONE_UPDATE_QUERY =
            "UPDATE telephone SET country_code=?, number=?, type=?, comment=?, contact_id=?" +
                    "WHERE id=?;";


    @Override
    public ArrayList<Telephone> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Telephone> telephones = new ArrayList<Telephone>();
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(TELEPHONE_FIND_ALL_QUERY);
            ResultSet telephoneResultSet = statement.executeQuery();
            while (telephoneResultSet.next()) {
                telephones.add(readEntityFrom(telephoneResultSet));
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding telephone", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return telephones;
    }

    public ArrayList<Telephone> findAllByContactId(Integer contactId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Telephone> telephones = new ArrayList<Telephone>();
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(TELEPHONE_FIND_ALL_BY_CONTACT_ID_QUERY);
            statement.setInt(1,contactId);
            ResultSet telephoneResultSet = statement.executeQuery();
            while (telephoneResultSet.next()) {
                telephones.add(readEntityFrom(telephoneResultSet));
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding telephones", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return telephones;
    }

    @Override
    protected Telephone readEntityFrom(ResultSet telephoneResultSet) throws SQLException {
        Integer i=1;
        Integer id = telephoneResultSet.getInt(i++);
        String countryCode = telephoneResultSet.getString(i++);
        String number = telephoneResultSet.getString(i++);
        String type = telephoneResultSet.getString(i++);
        String comment = telephoneResultSet.getString(i++);
        return new Telephone(id,countryCode,number,type,comment);
    }

    @Override
    public Telephone findEntityById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Telephone telephone = null;
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(TELEPHONE_SELECT_QUERY);
            statement.setInt(1,id);
            ResultSet telephones = statement.executeQuery();
            while (telephones.next()) {
                telephone = readEntityFrom(telephones);
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding telephone", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return telephone;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(TELEPHONE_DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting telephone", ex);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
    }

    @Override
    public void create(Telephone telephone) {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(TELEPHONE_CREATE_QUERY);
            statement.setString(i++,telephone.getCountry_code());
            statement.setString(i++,telephone.getNumber());
            statement.setString(i++,telephone.getType());
            statement.setString(i++,telephone.getComment());
            statement.setInt(i++,telephone.getContactId());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating telephone", ex);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
    }

    @Override
    public void update(Telephone telephone) {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(TELEPHONE_UPDATE_QUERY);
            statement.setString(i++,telephone.getCountry_code());
            statement.setString(i++,telephone.getNumber());
            statement.setString(i++,telephone.getType());
            statement.setString(i++,telephone.getComment());
            statement.setInt(i++,telephone.getContactId());
            statement.setInt(i++,telephone.getId());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating telephone", ex);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
    }
}
