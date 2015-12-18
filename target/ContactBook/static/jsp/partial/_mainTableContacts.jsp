<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/9/2015
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contacts" scope="request" type="java.util.List<com.itechart.app.entity.Contact>"/>
<form action="/ContactBook/main" method="post">
    <table class="table table-stripped">
        <tbody id="akk_table">
        <tr>
            <th></th>
            <th class="col-lg-3">Full name</th>
            <th class="col-lg-2">Date of birth</th>
            <th class="col-lg-4">Address</th>
            <th class="col-lg-3">
                <span class="col-lg-5" style="padding-left: 0;">Comment</span>
            <span class="col-lg-7">
                <span class="btn-group pull-right" role="group">
                    <button type="submit" name="mainFormButton" value="delete_contacts" id="accountDelete" class="btn btn-danger btn-sm">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </button>
                    <button type="submit" name="mainFormButton"  value="send_email"  id="sendEmailButton" class="btn btn-primary btn-sm">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                    </button>
                    <button type="submit" name="mainFormButton"  value="add_contact" id="accountAddButton" class="btn btn-success btn-sm">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </span>
            </span>
            </th>
        </tr>

        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td><input name="choseContactsCheckbox" type="checkbox" value="${contact.id}"></td>
                <td><a href="/ContactBook/edit?id=${contact.id}" >${contact.fullName}</a></td>
                <td>${contact.dateOfBirth}</td>
                <td>${contact.fullAddress}</td>
                <td>${contact.currentWorkplace}</td>
            </tr>
        </c:forEach>
        <span class="not-visible" id="choseContacts"></span>
        </tbody>
    </table>
    <c:import url="${pageContext.request.contextPath}/static/jsp/partial/_pagination.jsp"/>
</form>