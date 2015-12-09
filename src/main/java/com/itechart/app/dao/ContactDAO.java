package com.itechart.app.dao;

import com.itechart.app.entity.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maxim on 12/1/2015.
 */
public class ContactDAO extends AbstractDAO<Integer, Contact> {

    private static final String CONTACT_FIND_ALL_QUERY =
            "SELECT id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality, family_status, web_site, email, current_workplace, photo_url FROM contact WHERE is_deleted=0;";
    private static final String CONTACT_SELECT_QUERY =
            "SELECT id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality, family_status, web_site, email, current_workplace, photo_url FROM contact WHERE id = ? AND is_deleted=0;";
    private static final String CONTACT_DELETE_QUERY =
            "UPDATE contact SET is_deleted=1, date_deleted=NOW() WHERE id=?;";
    private static final String CONTACT_CREATE_QUERY =
            "INSERT INTO contact (first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality, family_status, web_site, email, current_workplace, photo_url)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String CONTACT_UPDATE_QUERY =
            "UPDATE contact SET first_name=?, last_name=?, patronymic=?, date_of_birth=?, sex_is_male=?, nationality=?, family_status=?, web_site=?, email=?, current_workplace=?, photo_url=?" +
                    "WHERE id=?;";


    @Override
    public ArrayList<Contact> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        Contact contact = null;
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(CONTACT_FIND_ALL_QUERY);
            ResultSet contactsResultSet = statement.executeQuery();
            while (contactsResultSet.next()) {
                contact = readEntityFrom(contactsResultSet);
                contacts.add(contact);
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding contact", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return contacts;
    }

    @Override
    public Contact findEntityById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Contact contact = null;
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(CONTACT_SELECT_QUERY);
            ResultSet contacts = statement.executeQuery();
            while (contacts.next()) {
                contact = readEntityFrom(contacts);
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding contact", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return contact;
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
        String webStatus = contact.getString(index++);
        String email =  contact.getString(index++);
        String currentWorkplace = contact.getString(index++);
        String photoUrl =  contact.getString(index++);

        return new Contact( id,firstName,lastName,patronymic,
                dateOfBirth,sexIsMale,nationality,familyStatus,
                webStatus,email,currentWorkplace,photoUrl);
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(CONTACT_DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting contact", ex);
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
    public void create(Contact contact) {
        Integer i=1;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(CONTACT_CREATE_QUERY);
            statement.setString(i++,contact.getFirstName());
            statement.setString(i++,contact.getLastName());
            statement.setString(i++,contact.getPatronymic());
            statement.setDate(i++,new java.sql.Date(contact.getDateOfBirth().getTime()));
            statement.setInt(i++,contact.isSexIsMale()?1:0);
            statement.setString(i++,contact.getNationality());
            statement.setString(i++,contact.getFamilyStatus());
            statement.setString(i++,contact.getWebSite());
            statement.setString(i++,contact.getEmail());
            statement.setString(i++,contact.getCurrentWorkplace());
            statement.setString(i++,contact.getPhotoUrl());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting contact", ex);
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
    public void update(Contact contact) {

        Connection connection = null;
        PreparedStatement statement = null;
        int i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(CONTACT_UPDATE_QUERY);
            statement.setString(i++,contact.getFirstName());
            statement.setString(i++,contact.getLastName());
            statement.setString(i++,contact.getPatronymic());
            statement.setDate(i++,new java.sql.Date(contact.getDateOfBirth().getTime()));
            statement.setInt(i++,contact.isSexIsMale()?1:0);
            statement.setString(i++,contact.getNationality());
            statement.setString(i++,contact.getFamilyStatus());
            statement.setString(i++,contact.getWebSite());
            statement.setString(i++,contact.getEmail());
            statement.setString(i++,contact.getCurrentWorkplace());
            statement.setString(i++,contact.getPhotoUrl());
            statement.setInt(i++,contact.getId());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting contact", ex);
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
