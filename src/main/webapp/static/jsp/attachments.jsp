<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="attachments" scope="request" type="java.util.List<com.itechart.app.entity.Attachment>"/>
<jsp:useBean id="contact" scope="request" type="com.itechart.app.entity.Contact"/>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/17/2015
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/_navbar.jsp"/>
<div class="container form-horizontal">
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8"><h3><small>Attachments of </small><a href="/ContactBook/edit?id=${contact.id}">${contact.fullName}</a></h3></div>
    </div>
    <div class="row">
        <div class="col-lg-2"></div>
        <form enctype="multipart/form-data">

            <div class="form-horizontal" role="form">
                <div class="form-group">

                    <div class="col-lg-8">
                        <table class="table table-condensed table-stripped">
                            <tbody id="att_table">
                            <tr>
                                <th class="col-lg-1"></th>
                                <th class="col-lg-3">Name</th>
                                <th class="col-lg-3">Date of upload</th>
                                <th class="col-lg-5">Comment</th>
                            </tr>
                            <c:forEach var="attachment" items="${attachments}">
                                <tr id="att_id_${attachment.id}">
                                    <td>
                                        <label for="att_check_${attachment.id}" class="sr-only"></label>
                                        <input type="checkbox" name="att_id" id="att_check_${attachment.id}" value="${attachment.id}">
                                    </td>
                                    <td><a href="/ContactBook/attachment?id=${attachment.contactId}&file=${attachment.id}" target="_blank">${attachment.filename}</a></td>
                                    <td>${attachment.stringDate}</td>
                                    <td>${attachment.comment}</td>
                                </tr>
                            </c:forEach>
                            <span class="not-visible" id="deletedAttachments"></span>
                            <span class="not-visible" id="newAttachments"></span>
                            <span class="not-visible" id="updatedAttachments"></span>
                            </tbody>
                        </table>

                    </div>

                </div>
            </div>

        </form>
    </div>
</div>
</body>
</html>
