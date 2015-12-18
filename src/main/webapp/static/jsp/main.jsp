<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/1/2015
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contact Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/Bootstrap_cosmo.css">
    <script src="${pageContext.request.contextPath}/static/js/contact.js"></script>
</head>

<body>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/_navbar.jsp"/>
    <div class="container">
        <c:import url="${pageContext.request.contextPath}/static/jsp/partial/_mainTableContacts.jsp"></c:import>
    </div>
<input type="hidden" id="page_number" value="${pageNumber}">
</body>
</html>
