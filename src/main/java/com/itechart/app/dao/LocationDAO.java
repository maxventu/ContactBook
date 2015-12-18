package com.itechart.app.dao;

import com.itechart.app.entity.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Maxim on 12/9/2015.
 */
public class LocationDAO extends AbstractDAO<String,Location>{
    public static LocationDAO INSTANCE = new LocationDAO();

    private LocationDAO() {
    }

    private static final String LOCATION_MAX_ID = "select MAX(id) FROM location";

    private static final String LOCATION_FIND_ALL_QUERY =
            "SELECT postcode,country,city  FROM location WHERE is_deleted=0";
    private static final String LOCATION_SELECT_QUERY =
            "SELECT postcode,country,city  FROM location WHERE postcode = ?";
    private static final String LOCATION_DELETE_QUERY ="";
    private static final String LOCATION_CREATE_QUERY =
            "INSERT INTO location (postcode,country,city) VALUES (?, ?, ?)";
    private static final String LOCATION_UPDATE_QUERY =
            "UPDATE location SET country=?, city=? WHERE postcode=?";

    @Override
    public PreparedStatement prepareStatementFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(LOCATION_FIND_ALL_QUERY);
    }

    @Override
    public PreparedStatement prepareStatementFindEntityById(Connection connection, String postcode) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(LOCATION_SELECT_QUERY);
        statement.setString(1, postcode);
        return statement;
    }

    @Override
    public PreparedStatement prepareStatementDelete(Connection connection, String id) throws SQLException {
        return connection.prepareStatement(LOCATION_DELETE_QUERY);
    }

    @Override
    public PreparedStatement prepareStatementCreate(Connection connection, Location location) throws SQLException {
        PreparedStatement statement = null;
        Integer i=1;
        statement = connection.prepareStatement(LOCATION_CREATE_QUERY);
        statement.setString(i++, location.getId());
        statement.setString(i++, location.getCountry());
        statement.setString(i, location.getCity());
        return  statement;
    }

    @Override
    public PreparedStatement prepareStatementUpdate(Connection connection, Location location) throws SQLException {
        PreparedStatement statement = null;
        Integer i=1;
        statement = connection.prepareStatement(LOCATION_UPDATE_QUERY);
        statement.setString(i++, location.getCountry());
        statement.setString(i++, location.getCity());
        statement.setString(i, location.getId());
        return  statement;
    }

    @Override
    protected PreparedStatement prepareStatementMaxRow(Connection connection) throws SQLException {
        return connection.prepareStatement(LOCATION_MAX_ID);
    }

    @Override
    protected Location readEntityFrom(ResultSet entityResultSet) throws SQLException {
        Integer i=1;
        String postcode = entityResultSet.getString(i++);
        String country = entityResultSet.getString(i++);
        String city = entityResultSet.getString(i);
        return new Location(postcode, country, city);
    }

    @Override
    protected String readKeyFrom(ResultSet entityResultSet) throws SQLException {
        return entityResultSet.getString(1);
    }
}
