<jsp:useBean id="contactId" scope="request" type="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/15/2015
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="attachmentAddModal" tabindex="-1" role="dialog" aria-labelledby="attachmentAddModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="upload_attachment" method="post" target="hiddenAttachmentFrame" enctype="multipart/form-data">
                <input type="hidden" id="attachmentAddModal_id"/>
                <input type="hidden" id="modal_contact_id" name="cont_id" value="${contactId}"/>
                <div class="modal-header">
                    <button type="button" class="close attachmentAddModal-close-modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="attachmentAddModalLabel">Attachment</h4>
                </div>
                <div class="modal-body">
                    <div class="form-horizontal" role="form">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-4">
                                    <label for="attachmentAddModal_filename">File name</label>
                                    <input type="text" name="attachment_filename" class="form-control" id="attachmentAddModal_filename" placeholder="Filename"  maxlength="45"/>
                                </div>
                                <div class="col-lg-8">
                                    <label for="attachmentAddModal_file">File</label>
                                    <input type="file" name="attachment_file" class="form-control"  id="attachmentAddModal_file"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label class="sr-only" for="attachmentAddModal_comment">Comment</label>
                                    <input type="text" name="attachment_comment" class="form-control" id="attachmentAddModal_comment" placeholder="Add comments here" maxlength="100"/>
                                </div>
                            </div>
                        </div>

                        <div class="alert alert-danger" role="alert" id="attachmentAddModalAlert" style="display:none;">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default attachmentAddModal-close-modal">Close</button>
                    <input type="submit" class="btn btn-primary" id="attachmentAddModalApply" value="Apply">
                </div>
            </form>
        </div>
    </div>
</div>
