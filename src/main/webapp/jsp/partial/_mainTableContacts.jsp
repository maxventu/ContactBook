<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/9/2015
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-stripped">
    <tr>
        <th></th>
        <th>Full name</th>
        <th>Date of birth</th>
        <th>Address</th>
        <th>Company</th>
    </tr>
    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td><input type="checkbox" checkboxid="${contact.id}"></td>
            <td><a href="/ContactBook/edit?id=${contact.id}" >${contact.fullName}</a></td>
            <td>${contact.dateOfBirth}</td>
            <td>${contact.fullAddress}</td>
            <td>${contact.currentWorkplace}</td>
        </tr>
    </c:forEach>
</table>