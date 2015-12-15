<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/13/2015
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="attachments" scope="request" type="java.util.List<com.itechart.app.entity.Attachment>"/>

<div class="form-horizontal" role="form">
    <div class="form-group">
        <label for="telephonesTable" class="col-lg-2 control-label">Attachments</label>
        <div class="col-lg-10">
            <div class="panel panel-default" id="telephonesTable">
                <div class="panel-body">
                    <table class="table table-condensed table-stripped">
                        <tbody id="att_table">
                            <tr>
                                <th class="col-lg-1"></th>
                                <th class="col-lg-3">Name</th>
                                <th class="col-lg-3">Date of upload</th>
                                <th class="col-lg-5">
                                    <span class="col-lg-5" style="padding-left: 0;">Comment</span>
                                    <span class="col-lg-7">
                                        <span class="btn-group pull-right" role="group">
                                          <button type="button" class="btn btn-danger btn-sm" id="attachmentDelete">
                                              <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                          </button>
                                          <button type="button" class="btn btn-primary btn-sm" id="attachmentEditButton">
                                              <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                          </button>
                                          <button type="button" class="btn btn-success btn-sm" id="attachmentAddButton">
                                              <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
                                          </button>
                                        </span>
                                    </span>
                                </th>
                            </tr>
                            <c:forEach var="attachment" items="${attachments}">
                                <tr id="att_id_${attachment.id}">
                                    <td>
                                        <label for="att_check_${attachment.id}" class="sr-only"></label>
                                        <input type="checkbox" id="att_check_${attachment.id}">
                                    </td>
                                    <td><a href="/ContactBook/attachment?id=${attachment.id}" >${attachment.filename}</a></td>
                                    <td>${attachment.dateUpload}</td>
                                    <td>${attachment.comment}</td>
                                    <input type="hidden" id="att_filename_${attachment.id}" value="${attachment.filename}">
                                    <input type="hidden" id="att_date_upload_${attachment.id}" value="${attachment.dateUpload}">
                                    <input type="hidden" id="att_comment_${attachment.id}" value="${attachment.comment}">
                                </tr>
                            </c:forEach>
                            <span class="not-visible" id="deletedAttachments"></span>
                            <span class="not-visible" id="newAttachments"></span>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>