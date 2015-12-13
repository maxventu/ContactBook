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
                        <tr>
                            <th class="col-lg-1"></th>
                            <th class="col-lg-4">Name</th>
                            <th class="col-lg-3">Date of upload</th>
                            <th class="col-lg-4">
                                <span class="col-lg-5" style="padding-left: 0;">Comment</span>
                                <span class="col-lg-7">
                                    <span class="btn-group pull-right" role="group">
                                      <button type="button" class="btn btn-danger btn-sm">
                                          <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                      </button>
                                      <button type="button" class="btn btn-primary btn-sm" >
                                          <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                      </button>
                                      <button type="button" class="btn btn-success btn-sm">
                                          <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
                                      </button>
                                    </span>
                                </span>
                            </th>
                        </tr>
                        <c:forEach var="attachment" items="${attachments}">
                            <tr>
                                <td>
                                    <label for="att_${attachment.id}" class="sr-only"></label>
                                    <input type="checkbox" id="att_${attachment.id}">
                                </td>
                                <td>${attachment.filename}</td>
                                <td>${attachment.dateUpload}</td>
                                <td>${attachment.comment}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>