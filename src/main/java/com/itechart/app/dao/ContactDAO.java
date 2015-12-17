package com.itechart.app.dao;

import com.itechart.app.controller.helpers.DateHelper;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Maxim on 12/1/2015.
 */
public class ContactDAO extends AbstractDAO<Integer, Contact> {
    public static ContactDAO INSTANCE = new ContactDAO();

    private ContactDAO() {
    }

    private static final String CONTACT_MAX_ID = "select MAX(id) FROM contact";

    private static final String CONTACT_FIND_ALL_QUERY =
            "SELECT id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality," +
                    " family_status, web_site, email, current_workplace, photo_url, street, house, apartment, location_postcode FROM contact WHERE is_deleted=0";
    private static final String CONTACT_SELECT_QUERY =
            "SELECT id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality," +
                    " family_status, web_site, email, current_workplace, photo_url, street, house, apartment, location_postcode FROM contact WHERE id = ? AND is_deleted=0";
    private static final String CONTACT_DELETE_QUERY =
            "UPDATE contact SET is_deleted=1, date_deleted=NOW() WHERE id=?";
    private static final String CONTACT_CREATE_QUERY =
            "INSERT INTO contact (id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality," +
                    " family_status, web_site, email, current_workplace, photo_url, street, house, apartment, location_postcode)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CONTACT_UPDATE_QUERY =
            "UPDATE contact SET first_name=?, last_name=?, patronymic=?, date_of_birth=?, sex_is_male=?, nationality=?," +
                    " family_status=?, web_site=?, email=?, current_workplace=?, photo_url=?,street=?, house=?, apartment=?, location_postcode=?" +
                    " WHERE id=?";


    @Override
    public PreparedStatement prepareStatementFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(CONTACT_FIND_ALL_QUERY);
    }

    @Override
    public PreparedStatement prepareStatementFindEntityById(Connection connection, Integer id) throws SQLException {
        PreparedStatement statement = null;
        statement =  connection.prepareStatement(CONTACT_SELECT_QUERY);
        statement.setInt(1,id);
        return statement;
    }

    @Override
    public PreparedStatement prepareStatementDelete(Connection connection, Integer id) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(CONTACT_DELETE_QUERY);
        statement.setInt(1,id);
        return statement;
    }

    @Override
    public PreparedStatement prepareStatementCreate(Connection connection, Contact contact) throws SQLException {
        PreparedStatement statement = null;
        int i=1;
        statement = connection.prepareStatement(CONTACT_CREATE_QUERY);
        statement.setInt(i++, contact.getId());
        statement.setString(i++,contact.getFirstName());
        statement.setString(i++,contact.getLastName());
        statement.setString(i++,contact.getPatronymic());
        java.sql.Date date = DateHelper.INSTANCE.getSqlDate(contact.getDateOfBirth());
        statement.setDate(i++,date);
        statement.setInt(i++,contact.isSexIsMale()?1:0);
        statement.setString(i++,contact.getNationality());
        statement.setString(i++,contact.getFamilyStatus());
        statement.setString(i++,contact.getWebSite());
        statement.setString(i++,contact.getEmail());
        statement.setString(i++,contact.getCurrentWorkplace());
        statement.setString(i++,contact.getPhotoUrl());
        statement.setString(i++,contact.getStreet());
        statement.setString(i++,contact.getHouse());
        statement.setString(i++,contact.getApartment());
        statement.setString(i++,contact.getPostcode());
        return statement;
    }

    @Override
    public PreparedStatement prepareStatementUpdate(Connection connection, Contact contact) throws SQLException {
        PreparedStatement statement = null;
        int i=1;
        statement = connection.prepareStatement(CONTACT_UPDATE_QUERY);
        statement.setString(i++,contact.getFirstName());
        statement.setString(i++,contact.getLastName());
        statement.setString(i++,contact.getPatronymic());
        java.sql.Date date = DateHelper.INSTANCE.getSqlDate(contact.getDateOfBirth());
        statement.setDate(i++,date);
        statement.setInt(i++,contact.isSexIsMale()?1:0);
        statement.setString(i++,contact.getNationality());
        statement.setString(i++,contact.getFamilyStatus());
        statement.setString(i++,contact.getWebSite());
        statement.setString(i++,contact.getEmail());
        statement.setString(i++,contact.getCurrentWorkplace());
        statement.setString(i++,contact.getPhotoUrl());
        statement.setString(i++, contact.getStreet());
        statement.setString(i++, contact.getHouse());
        statement.setString(i++, contact.getApartment());
        statement.setString(i++, contact.getPostcode());
        statement.setInt(i++, contact.getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareStatementMaxRow(Connection connection) throws SQLException {
        return connection.prepareStatement(CONTACT_MAX_ID);
    }

    @Override
    protected Contact readEntityFrom(ResultSet contact) throws SQLException {
        Integer index = 1;
        Integer id = contact.getInt(index++);
        String firstName = contact.getString(index++);
        String lastName = contact.getString(index++);
        String patronymic =  contact.getString(index++);
        Date dateOfBirth = contact.getDate(index++);
        Boolean sexIsMale = contact.getBoolean(index++);
        String nationality =  contact.getString(index++);
        String familyStatus = contact.getString(index++);
        String webSite = contact.getString(index++);
        String email =  contact.getString(index++);
        String currentWorkplace = contact.getString(index++);
        String photoUrl =  contact.getString(index++);
        String street =  contact.getString(index++);
        String house =  contact.getString(index++);
        String apartment =  contact.getString(index++);
        String postcode =  contact.getString(index++);
        Location location = LocationDAO.INSTANCE.findEntityById(postcode);
        String country = null;
        String city = null;
        if(location!=null){
            country = location.getCountry();
            city = location.getCity();
        }
        return new Contact( id,
                 firstName,
                 lastName,
                 patronymic,
                 dateOfBirth,
                 sexIsMale,
                 nationality,
                 familyStatus,
                 webSite,
                 email,
                 currentWorkplace,
                 photoUrl,
                 street,
                 house,
                 apartment,
                 postcode,
                 country,
                 city);
    }

    @Override
    protected Integer readKeyFrom(ResultSet entityResultSet) throws SQLException {
        return entityResultSet.getInt(1);
    }

}
