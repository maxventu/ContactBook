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
    <link rel="stylesheet" href="../css/Bootstrap_cosmo.css">
</head>
<body>

<div class="container">
    <form id="data" method="POST" action="upload" enctype="multipart/form-data">
        <input type="hidden" id="${contact.id}">
        <div class="row">
            <div class="col-lg-10">
                <c:import url="partial/_contactEditForm.jsp"/>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10">
                <c:import url="partial/_telephonesTable.jsp"/>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-10">
                <c:import url="partial/_attachmentsTable.jsp"/>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
