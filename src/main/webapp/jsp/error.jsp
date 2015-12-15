<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 11/25/2015
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8"%>
<html><title>Error Page</title>
<body>
<c:import url="partial/_navbar.jsp"/>
<div class="container">
    <div class="form-horizontal">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">Request from ${pageContext.errorData.requestURI} is failed</div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">Status code: ${pageContext.errorData.statusCode}</div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">Exception: ${pageContext.exception}</div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">Message from exception: ${pageContext.exception.message}</div>
        </div>
    </div>
</div>
</body>
</html>
