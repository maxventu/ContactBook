package com.itechart.app.dao;

import com.itechart.app.entity.Attachment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maxim on 12/9/2015.
 */
public class AttachmentDAO extends AbstractDAO<Integer,Attachment> {
    private static final String ATTACHMENT_FIND_ALL_BY_CONTACT_ID_QUERY =
            "SELECT id,filename,date_upload,comment,contact_id FROM attachment WHERE contact_id = ? AND is_deleted=0;";
    private static final String ATTACHMENT_FIND_ALL_QUERY =
            "SELECT id,filename,date_upload,comment,contact_id FROM attachment WHERE is_deleted=0;";
    private static final String ATTACHMENT_SELECT_QUERY =
            "SELECT id,filename,date_upload,comment,contact_id FROM attachment WHERE id = ? AND is_deleted=0;";
    private static final String ATTACHMENT_DELETE_QUERY =
            "UPDATE attachment SET is_deleted=1, date_deleted=NOW() WHERE id=?;";
    private static final String ATTACHMENT_CREATE_QUERY =
            "INSERT INTO attachment (filename,date_upload,comment,contact_id)" +
                    "VALUES (?, CURDATE(), ?, ?);";
    private static final String ATTACHMENT_UPDATE_QUERY =
            "UPDATE attachment SET filename=?, comment=?" +
                    "WHERE id=?;";
    @Override
    public ArrayList<Attachment> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Attachment> attachments = new ArrayList<Attachment>();
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ATTACHMENT_FIND_ALL_QUERY);
            ResultSet attachmentResultSet = statement.executeQuery();
            while (attachmentResultSet.next()) {
                attachments.add(readEntityFrom(attachmentResultSet));
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding attachments", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return attachments;
    }

    @Override
    protected Attachment readEntityFrom(ResultSet attachmentResultSet) throws SQLException {
        Integer i=1;
        Integer id = attachmentResultSet.getInt(i++);
        String filename = attachmentResultSet.getString(i++);
        Date dateUpload = attachmentResultSet.getDate(i++);
        String comment = attachmentResultSet.getString(i++);
        Integer contactId = attachmentResultSet.getInt(i++);
        return new Attachment(id, filename, dateUpload, comment, contactId);
    }

    @Override
    public Attachment findEntityById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Attachment attachment = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ATTACHMENT_SELECT_QUERY);
            statement.setInt(1,id);
            ResultSet attachmentResultSet = statement.executeQuery();
            while (attachmentResultSet.next()){
                attachment = readEntityFrom(attachmentResultSet);
            }
        }
        catch (Exception ex){
            LOGGER.error("something went wrong",ex);
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception e){
                LOGGER.error("connection wasn't closed",e);
            }
        }
        return attachment;
    }
    public ArrayList<Attachment> findAllByContactId(Integer contactId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Attachment> attachments = new ArrayList<Attachment>();
        try {
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ATTACHMENT_FIND_ALL_BY_CONTACT_ID_QUERY);
            statement.setInt(1,contactId);
            ResultSet attachmentsResultSet = statement.executeQuery();
            while (attachmentsResultSet.next()) {
                attachments.add(readEntityFrom(attachmentsResultSet));
            }
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong while finding attachments", exception);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Connection is not closed", e);
            }
        }
        return attachments;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ATTACHMENT_DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in deleting attachment", ex);
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
    public void create(Attachment attachment) {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ATTACHMENT_CREATE_QUERY);
            statement.setString(i++,attachment.getFilename());
            statement.setString(i++,attachment.getComment());
            statement.setInt(i++,attachment.getContactId());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating attachment", ex);
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
    public void update(Attachment attachment) {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer i=1;
        try{
            connection = ConnectorDB.getConnection();
            statement = connection.prepareStatement(ATTACHMENT_UPDATE_QUERY);
            statement.setString(i++,attachment.getFilename());
            statement.setString(i++,attachment.getComment());
            statement.setInt(i++,attachment.getId());
            statement.executeQuery();
        }
        catch (SQLException ex){
            LOGGER.error("Something went wrong in creating attachment", ex);
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
