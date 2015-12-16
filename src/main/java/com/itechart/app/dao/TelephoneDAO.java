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
    public static TelephoneDAO INSTANCE = new TelephoneDAO();

    private TelephoneDAO() {
    }

    private static final String TELEPHONE_MAX_ID = "select MAX(id) FROM telephone";

    private static final String TELEPHONE_FIND_ALL_BY_CONTACT_ID_QUERY =
            "SELECT id,country_code,operator_code,number,type,comment,contact_id  FROM telephone WHERE contact_id = ? AND is_deleted=0;";
    private static final String TELEPHONE_FIND_ALL_QUERY =
            "SELECT id,country_code,operator_code,number,type,comment,contact_id  FROM telephone WHERE is_deleted=0;";
    private static final String TELEPHONE_SELECT_QUERY =
            "SELECT id,country_code,operator_code,number,type,comment,contact_id  FROM telephone WHERE id = ? AND is_deleted=0;";
    private static final String TELEPHONE_DELETE_QUERY =
            "UPDATE telephone SET is_deleted=1, date_deleted=NOW() WHERE id=?;";
    private static final String TELEPHONE_CREATE_QUERY =
            "INSERT INTO telephone (id,country_code,operator_code,number,type,comment,contact_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String TELEPHONE_UPDATE_QUERY =
            "UPDATE telephone SET country_code=?,operator_code=?, number=?, type=?, comment=?, contact_id=?" +
                    "WHERE id=?;";

    @Override
    protected PreparedStatement prepareStatementFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(TELEPHONE_FIND_ALL_QUERY);
    }

    @Override
    protected PreparedStatement prepareStatementFindEntityById(Connection connection, Integer id) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(TELEPHONE_SELECT_QUERY);
        statement.setInt(1,id);
        return statement;
    }

    @Override
    protected PreparedStatement prepareStatementDelete(Connection connection, Integer id) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(TELEPHONE_DELETE_QUERY);
        statement.setInt(1,id);
        return statement;
    }

    @Override
    protected PreparedStatement prepareStatementCreate(Connection connection, Telephone telephone) throws SQLException {
        PreparedStatement statement = null;
        int i=1;
        statement = connection.prepareStatement(TELEPHONE_CREATE_QUERY);
        statement.setInt(i++, telephone.getId());
        statement.setString(i++,telephone.getCountryCode());
        statement.setString(i++,telephone.getOperatorCode());
        statement.setString(i++,telephone.getNumber());
        statement.setString(i++,telephone.getType());
        statement.setString(i++,telephone.getComment());
        statement.setInt(i++,telephone.getContactId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareStatementUpdate(Connection connection, Telephone telephone) throws SQLException {
        PreparedStatement statement = null;
        int i=1;
        statement = connection.prepareStatement(TELEPHONE_UPDATE_QUERY);
        statement.setString(i++,telephone.getCountryCode());
        statement.setString(i++,telephone.getOperatorCode());
        statement.setString(i++,telephone.getNumber());
        statement.setString(i++,telephone.getType());
        statement.setString(i++,telephone.getComment());
        statement.setInt(i++,telephone.getContactId());
        statement.setInt(i++,telephone.getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareStatementMaxRow(Connection connection) throws SQLException {
        return connection.prepareStatement(TELEPHONE_MAX_ID);
    }

    public ArrayList<Telephone> findAllByContactId(Integer contactId){
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
        String operatorCode = telephoneResultSet.getString(i++);
        String number = telephoneResultSet.getString(i++);
        String type = telephoneResultSet.getString(i++);
        String comment = telephoneResultSet.getString(i++);
        Integer contactId = telephoneResultSet.getInt(i++);
        return new Telephone(id,countryCode,operatorCode,number,type,comment,contactId);
    }

    @Override
    protected Integer readKeyFrom(ResultSet entityResultSet) throws SQLException {
        return entityResultSet.getInt(1);
    }
}
