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
<table class="table table-stripped">
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