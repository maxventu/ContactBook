<%@ page import="com.itechart.app.entity.helpers.TelephoneType" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/12/2015
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("telephoneTypes", TelephoneType.values());
    pageContext.setAttribute("telephoneType", TelephoneType.MOB);
%>
<jsp:useBean id="telephones" scope="request" type="java.util.List<com.itechart.app.entity.Telephone>"/>

<div class="form-horizontal" role="form">
    <div class="form-group">
        <label for="telephonesTable" class="col-lg-2 control-label">Telephones</label>
        <div class="col-lg-10">
            <div class="panel panel-default" id="telephonesTable">
                <div class="panel-body">

                    <table class="table table-condensed table-stripped">
                        <tr>
                            <th class="col-lg-1"></th>
                            <th class="col-lg-4">Telephone</th>
                            <th class="col-lg-3">Type</th>
                            <th class="col-lg-4">
                                <span class="col-lg-5" style="padding-left: 0;">Comment</span>
                                <span class="col-lg-7">
                                    <span class="btn-group pull-right" role="group">
                                        <button type="button" class="btn btn-danger btn-sm">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" class="btn btn-primary btn-sm" >
                                            <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm">
                                            <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        </button>
                                    </span>
                                </span>
                            </th>
                        </tr>
                        <c:forEach var="telephone" items="${telephones}">
                            <tr >
                                <td>
                                    <label for="tel_${telephone.id}" class="sr-only"></label>
                                    <input type="checkbox" id="tel_${telephone.id}">
                                </td>
                                <td>${telephone.fullNumber}</td>
                                <td>${telephoneType.get(telephone.type).value}</td>
                                <td>${telephone.comment}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>