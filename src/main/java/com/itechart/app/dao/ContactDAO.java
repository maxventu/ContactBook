package com.itechart.app.dao;

import com.itechart.app.controller.helpers.DateHelper;
import com.itechart.app.entity.Contact;
import com.itechart.app.entity.Location;
import com.itechart.app.entity.helpers.SearchContact;

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
    private static final String CONTACT_FIND_CONTACT =
            "SELECT id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality," +
                    " family_status, web_site, email, current_workplace, photo_url, street, house, apartment, location_postcode" +
                    " FROM contact LEFT JOIN location ON location_postcode=postcode WHERE is_deleted=0 ";
    private static final String CONTACT_FIND_BY_PAGE =
            "SELECT id, first_name, last_name, patronymic, date_of_birth, sex_is_male, nationality," +
                    " family_status, web_site, email, current_workplace, photo_url, street, house, apartment, location_postcode FROM contact WHERE is_deleted=0 LIMIT ?,?";


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

    public ArrayList<Contact> findContacts(SearchContact sc){
        LOGGER.debug("starting finding contacts");
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Contact> arrayList = new ArrayList<Contact>();
        ResultSet addressResultSet = null;
        try {
            connection = ConnectorDB.getConnection();
            statement = prepareStatementFindContacts(connection,sc);
            LOGGER.debug("finding contacts query={}",statement);
            if(statement!=null)
            addressResultSet = statement.executeQuery();
            while (addressResultSet.next()) {
                arrayList.add(readEntityFrom(addressResultSet));
            }
            //if(arrayList.isEmpty())arrayList.add(new T());
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding all", exception);
        }
        finally {
            try {
                if(connection!=null)
                    connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return arrayList;
    }

    private PreparedStatement prepareStatementFindContacts(Connection connection, SearchContact cont) throws SQLException {
    StringBuilder sb = new StringBuilder(CONTACT_FIND_CONTACT);
        if(cont==null)return null;
        appendLikeString(sb,"first_name",cont.getFirstName());
        appendLikeString(sb,"last_name",cont.getLastName());
        appendLikeString(sb,"patronymic",cont.getPatronymic());
        appendLikeString(sb,"sex_is_male",cont.getSexIsMale());
        appendLikeString(sb,"nationality",cont.getNationality());
        appendLikeString(sb,"family_status",cont.getFamilyStatus());
        appendLikeString(sb,"current_workplace",cont.getCurrentWorkplace());
        appendLikeString(sb,"street",cont.getStreet());
        appendLikeString(sb,"house",cont.getHouse());
        appendLikeString(sb,"apartment",cont.getApartment());
        appendLikeString(sb,"postcode",cont.getPostcode());
        appendLikeString(sb,"country",cont.getCountry());
        appendLikeString(sb,"city",cont.getCity());
        if(cont.getDateOfBirth()!=null){
            sb.append("AND date_of_birth "+(cont.getDateIsGreater()?">":"<")+"? ");
        }
        LOGGER.debug("created statement={}",sb);
        PreparedStatement statement = null;
        int i=1;
        statement = connection.prepareStatement(sb.toString());

        i= setStringIfNotNull(i, statement, cont.getFirstName());
        i= setStringIfNotNull(i, statement, cont.getLastName());
        i= setStringIfNotNull(i, statement, cont.getPatronymic());
        i= setStringIfNotNull(i, statement, cont.getSexIsMale());
        i= setStringIfNotNull(i, statement, cont.getNationality());
        i= setStringIfNotNull(i, statement, cont.getFamilyStatus());
        i= setStringIfNotNull(i, statement, cont.getCurrentWorkplace());
        i= setStringIfNotNull(i, statement, cont.getStreet());
        i= setStringIfNotNull(i, statement, cont.getHouse());
        i= setStringIfNotNull(i, statement, cont.getApartment());
        i= setStringIfNotNull(i, statement, cont.getPostcode());
        i= setStringIfNotNull(i, statement, cont.getCountry());
        i= setStringIfNotNull(i, statement, cont.getCity());
        if(cont.getDateOfBirth()!=null)statement.setDate(i, DateHelper.INSTANCE.getSqlDate(cont.getDateOfBirth()));
        return statement;
    }
    private void appendLikeString(StringBuilder sb,String paramName,String param){
        if(param!=null)sb.append("AND "+paramName+" LIKE ? ");
    }
    private int setStringIfNotNull(int i,PreparedStatement statement,String string) throws SQLException {
        if(string!=null){statement.setString(i,"%"+string+"%");return ++i;}
        return i;
    }

    public ArrayList<Contact> getContactsByPage(Integer pageNumber,Integer numberOfElements)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Contact> arrayList = new ArrayList<Contact>();
        try {
            connection = ConnectorDB.getConnection();
            statement = prepareStatementFindAllByPage(connection, pageNumber, numberOfElements);
            LOGGER.debug("contact by page query: {}",statement);
            ResultSet addressResultSet = statement.executeQuery();
            while (addressResultSet.next()) {
                arrayList.add(readEntityFrom(addressResultSet));
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding all", exception);
        }
        finally {
            try {
                if(connection!=null)
                    connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return arrayList;
    }

    private PreparedStatement prepareStatementFindAllByPage(Connection connection, Integer pageNumber, Integer numberOfElements) throws SQLException {
        PreparedStatement statement = null;
        int i=1;
        statement = connection.prepareStatement(CONTACT_FIND_BY_PAGE);
        statement.setInt(i++,(pageNumber-1)*numberOfElements);
        statement.setInt(i,numberOfElements);
        return statement;
    }



    public Integer countNotDeleted(){
        Connection connection = null;
        PreparedStatement statement = null;
        Integer numberOfNotDeleted = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement("SELECT COUNT(id) FROM contact WHERE is_deleted=0");
            LOGGER.debug("executing query {}",statement);
            ResultSet attachmentResultSet = statement.executeQuery();
            while (attachmentResultSet.next()){
                numberOfNotDeleted = readKeyFrom(attachmentResultSet);
                LOGGER.debug("numberOfNotDeleted is {}",numberOfNotDeleted);
            }
        }
        catch (Exception ex){
            LOGGER.error("something went wrong while finding entity",ex);
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception e){
                LOGGER.error("connection wasn't closed",e);
            }
        }
        return numberOfNotDeleted;
    }
}
