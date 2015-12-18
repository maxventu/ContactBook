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
<html>
<head>
    <title>Error Page</title>

</head>
<body>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/_navbar.jsp"/>
<div class="container">
    <div class="form-horizontal jumbotron">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8 "><h4><small>Request from </small>${pageContext.errorData.requestURI}<small> is failed</small></h4></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8"><h1><small>The code is: </small><span class="text-warning"> ${pageContext.errorData.statusCode}</span></h1></div>
        </div>
        <c:if test="${pageContext.exception != null}">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">Exception: ${pageContext.exception}</div>
            </div>
        </c:if>
        <c:if test="${pageContext.exception.message != null}">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">Message from exception: ${pageContext.exception.message}</div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
