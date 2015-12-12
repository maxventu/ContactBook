<%@ page import="com.itechart.app.entity.helpers.TelephoneType" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/12/2015
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("telephoneTypes", TelephoneType.values());
%>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="btn-group pull-right" role="group">
            <button type="button" class="btn btn-danger btn-xs">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
            </button>
            <button type="button" class="btn btn-primary btn-xs" >
                <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
            </button>
            <button type="button" class="btn btn-success btn-xs">
                <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
            </button>
        </div>
        <table class="table table-condensed table-stripped">
            <tr>
                <th></th>
                <th>Telephone</th>
                <th>Type</th>
                <th>Comment</th>
            </tr>
            <c:forEach var="telephone" items="${telephones}">
                <tr>
                    <td><input type="checkbox" checkboxid="tel_${telephone.id}"></td>
                    <td>${telephone.fullNumber}</td>
                    <td>${TelephoneType.get(telephone.type).value}</td>
                    <td>${telephone.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>