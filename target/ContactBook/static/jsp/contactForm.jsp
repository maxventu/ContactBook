<jsp:useBean id="contact" scope="request" type="com.itechart.app.entity.Contact"/>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/10/2015
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/Bootstrap_cosmo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/contactbook.css">
    <script src="${pageContext.request.contextPath}/static/js/attachment.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/telephone.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/contactEdit.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/avatar.js"></script>
</head>
<body>

<c:import url="${pageContext.request.contextPath}/static/jsp/partial/_navbar.jsp"/>
<div class="container">

    <form id="data" method="POST" action="/ContactBook/edit"  >
        <input type="hidden" name="id" id="contact_id" value="${contact.id}">
        <div class="row">
            <div class="col-lg-10">
                <c:import url="${pageContext.request.contextPath}/static/jsp/partial/_contactEditForm.jsp"/>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10">
                <c:import url="${pageContext.request.contextPath}/static/jsp/partial/telephone/_telephonesTable.jsp"/>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-10">
                <c:import url="${pageContext.request.contextPath}/static/jsp/partial/attachment/_attachmentsTable.jsp"/>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" name="save_button" value="save_contact" class="btn btn-default" onclick="return initAllTextValidations();">Submit</button>
            </div>
        </div>
    </form>
</div>

<c:import url="${pageContext.request.contextPath}/static/jsp/partial/telephone/_telephoneEditModal.jsp"/>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/attachment/_attachmentAddModal.jsp"/>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/attachment/_attachmentEditModal.jsp"/>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/avatar/_avatarEditModal.jsp"/>
</body>
</html>
