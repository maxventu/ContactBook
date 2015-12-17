<%@ page import="com.itechart.app.entity.helpers.TelephoneType" %>
<jsp:useBean id="telephones" scope="request" type="java.util.List<com.itechart.app.entity.Telephone>"/>
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


<div class="form-horizontal" role="form">
    <div class="form-group">
        <label for="telephonesTable" class="col-lg-2 control-label">Telephones</label>
        <div class="col-lg-10">
            <div class="panel panel-default" id="telephonesTable" style="margin-bottom: 0;">
                <div class="panel-body">

                    <table class="table table-condensed table-stripped" >
                        <tbody id="tel_table">
                        <tr>
                            <th class="col-lg-1"></th>
                            <th class="col-lg-3">Telephone</th>
                            <th class="col-lg-3">Type</th>
                            <th class="col-lg-5">
                                <span class="col-lg-5" style="padding-left: 0;">Comment</span>
                                <span class="col-lg-7">
                                    <span class="btn-group pull-right" role="group">
                                        <button type="button" class="btn btn-danger btn-sm" id="telephoneDelete" >
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" id="telephoneEditButton" class="btn btn-primary btn-sm">
                                            <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" id="telephoneAddButton" class="btn btn-success btn-sm">
                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        </button>
                                    </span>
                                </span>
                            </th>
                        </tr>
                        <c:forEach var="telephone" items="${telephones}">
                            <tr id="tel_id_${telephone.id}">
                                <td>
                                    <label for="tel_check_${telephone.id}" class="sr-only"></label>
                                    <input name="tel_id" type="checkbox" id="tel_check_${telephone.id}" value="${telephone.id}">
                                </td>
                                <td>${telephone.fullNumber}</td>
                                <td>${telephoneType.get(telephone.type).value}</td>
                                <td>${telephone.comment}</td>

                                <input type="hidden" name="tel_country_code" id="tel_country_code_${telephone.id}" value="${telephone.countryCode}">
                                <input type="hidden" name="tel_operator_code" id="tel_operator_code_${telephone.id}" value="${telephone.operatorCode}">
                                <input type="hidden" name="tel_number" id="tel_number_${telephone.id}" value="${telephone.number}">
                                <input type="hidden" name="tel_type" id="tel_type_select_${telephone.id}" value="${telephone.type}">
                                <input type="hidden" name="tel_comment" id="tel_comment_${telephone.id}" value="${telephone.comment}">
                            </tr>
                        </c:forEach>
                        <span class="not-visible" id="deletedTelephones"></span>
                        <span class="not-visible" id="newTelephones"></span>
                        <span class="not-visible" id="updatedTelephones"></span>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>