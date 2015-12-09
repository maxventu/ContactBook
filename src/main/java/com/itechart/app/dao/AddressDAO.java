package com.itechart.app.dao;

import com.itechart.app.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Maxim on 12/9/2015.
 */
public class AddressDAO extends AbstractDAO<Integer,Address>{
    private static final String ADDRESS_FIND_ALL_QUERY =
            "SELECT contact_id,country,city,street,house,apartment,postcode  FROM address WHERE is_deleted=0;";
    private static final String ADDRESS_SELECT_QUERY =
            "SELECT contact_id,country,city,street,house,apartment,postcode  FROM address WHERE contact_id = ? AND is_deleted=0;";
    private static final String ADDRESS_DELETE_QUERY =
            "UPDATE address SET is_deleted=1, date_deleted=NOW() WHERE id=?;";
    private static final String ADDRESS_CREATE_QUERY =
            "INSERT INTO address (contact_id,country,city,street,house,apartment,postcode) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String ADDRESS_UPDATE_QUERY =
            "UPDATE address SET country=?, city=?, street=?, house=?, apartment=?, postcode=?" +
                    "WHERE contact_id=?;";
    @Override
    public ArrayList<Address> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Address> addresses = new ArrayList<Address>();
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ADDRESS_FIND_ALL_QUERY);
            ResultSet addressResultSet = statement.executeQuery();
            while (addressResultSet.next()) {
                addresses.add(readEntityFrom(addressResultSet));
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding addresses", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return addresses;
    }

    @Override
    public Address findEntityById(Integer contactId) {
        Connection connection = null;
        PreparedStatement statement = null;
        Address address = null;
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ADDRESS_SELECT_QUERY);
            statement.setInt(1,contactId);
            ResultSet addresses = statement.executeQuery();
            while (addresses.next()) {
                address = readEntityFrom(addresses);
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding address", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return address;
    }

    @Override
    public void delete(Integer contactId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ADDRESS_DELETE_QUERY);
            statement.setInt(1,contactId);
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting address", ex);
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
    public void create(Address address) {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ADDRESS_CREATE_QUERY);
            statement.setInt(i++,address.getContactId());
            statement.setString(i++,address.getCountry());
            statement.setString(i++,address.getCity());
            statement.setString(i++,address.getStreet());
            statement.setString(i++,address.getHouse());
            statement.setString(i++,address.getApartment());
            statement.setString(i++,address.getPostcode());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating address", ex);
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
    public void update(Address address) {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ADDRESS_UPDATE_QUERY);
            statement.setString(i++,address.getCountry());
            statement.setString(i++,address.getCity());
            statement.setString(i++,address.getStreet());
            statement.setString(i++,address.getHouse());
            statement.setString(i++,address.getApartment());
            statement.setString(i++,address.getPostcode());
            statement.setInt(i++,address.getContactId());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating address", ex);
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
    protected Address readEntityFrom(ResultSet entityResultSet) throws SQLException {
        Integer i=1;
        String country = entityResultSet.getString(i++);
        String city = entityResultSet.getString(i++);
        String street = entityResultSet.getString(i++);
        String house = entityResultSet.getString(i++);
        String apartment = entityResultSet.getString(i++);
        String postcode = entityResultSet.getString(i++);
        Integer contactId = entityResultSet.getInt(i++);
        return new Address(country, city, street, house, apartment, postcode, contactId);
    }
}
