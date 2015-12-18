<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/18/2015
  Time: 1:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Email send successfully</title>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/_navbar.jsp"/>
<div class="container">
    <div class="form-horizontal">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-8">
                <h1>
                    Email sent successfully!
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="clo-lg-1"></div>
            <div class="col-lg-8">
                <a href="main"><button class="btn btn-default">Home</button></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
