package com.itechart.app.dao;

import com.itechart.app.controller.helpers.DateHelper;
import com.itechart.app.entity.Attachment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maxim on 12/9/2015.
 */
public class AttachmentDAO extends AbstractDAO<Integer,Attachment> {
    public static AttachmentDAO INSTANCE = new AttachmentDAO();

    private AttachmentDAO() {
    }

    private static final String ATTACHMENT_MAX_ID = "select MAX(id) FROM attachment";

    private static final String ATTACHMENT_FIND_ALL_BY_CONTACT_ID_QUERY =
            "SELECT id,filename,date_upload,comment,contact_id FROM attachment WHERE contact_id = ? AND is_deleted=0";
    private static final String ATTACHMENT_FIND_ALL_QUERY =
            "SELECT id,filename,date_upload,comment,contact_id FROM attachment WHERE is_deleted=0";
    private static final String ATTACHMENT_SELECT_QUERY =
            "SELECT id,filename,date_upload,comment,contact_id FROM attachment WHERE id = ? AND is_deleted=0";
    private static final String ATTACHMENT_DELETE_QUERY =
            "UPDATE attachment SET is_deleted=1, date_deleted=NOW() WHERE id=?";
    private static final String ATTACHMENT_CREATE_QUERY =
            "INSERT INTO attachment (id, filename,date_upload,comment,contact_id)" +
                    "VALUES (?, ?, ?, ?, ?)";
    private static final String ATTACHMENT_UPDATE_QUERY =
            "UPDATE attachment SET filename=?, comment=?" +
                    "WHERE id=?";
    @Override
    public PreparedStatement prepareStatementFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(ATTACHMENT_FIND_ALL_QUERY);
    }

    @Override
    public PreparedStatement prepareStatementFindEntityById(Connection connection,Integer id) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(ATTACHMENT_SELECT_QUERY);
        statement.setInt(1,id);
        return statement;
    }

    @Override
    public PreparedStatement prepareStatementDelete(Connection connection, Integer id) throws SQLException{
        PreparedStatement statement=null;
        statement = connection.prepareStatement(ATTACHMENT_DELETE_QUERY);
        statement.setInt(1,id);
        return  statement;
    }

    @Override
    public PreparedStatement prepareStatementCreate(Connection connection, Attachment attachment) throws SQLException{
        Integer i=1;
        PreparedStatement statement = null;
        statement = connection.prepareStatement(ATTACHMENT_CREATE_QUERY);
        statement.setInt(i++,attachment.getId());
        statement.setString(i++,attachment.getFilename());
        Timestamp timestamp = DateHelper.INSTANCE.getTimestamp(attachment.getDateUpload());
        statement.setTimestamp(i++, timestamp);
        statement.setString(i++,attachment.getComment());
        statement.setInt(i++,attachment.getContactId());
        return statement;
    }

    @Override
    public PreparedStatement prepareStatementUpdate(Connection connection,Attachment attachment) throws SQLException{
        PreparedStatement statement = null;
        Integer i=1;
        statement = connection.prepareStatement(ATTACHMENT_UPDATE_QUERY);
        statement.setString(i++,attachment.getFilename());
        statement.setString(i++,attachment.getComment());
        statement.setInt(i++,attachment.getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareStatementMaxRow(Connection connection) throws SQLException {
        return connection.prepareStatement(ATTACHMENT_MAX_ID);
    }

    @Override
    protected Attachment readEntityFrom(ResultSet attachmentResultSet) throws SQLException {
        Integer i=1;
        Integer id = attachmentResultSet.getInt(i++);
        String filename = attachmentResultSet.getString(i++);
        Date dateUpload = DateHelper.INSTANCE.getDate(attachmentResultSet.getTimestamp(i++));
        String comment = attachmentResultSet.getString(i++);
        Integer contactId = attachmentResultSet.getInt(i++);
        return new Attachment(id, filename, dateUpload, comment, contactId);
    }

    @Override
    protected Integer readKeyFrom(ResultSet entityResultSet) throws SQLException {
        return entityResultSet.getInt(1);
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
}
