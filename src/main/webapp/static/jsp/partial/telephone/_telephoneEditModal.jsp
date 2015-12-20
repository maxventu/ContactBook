<%@ page import="com.itechart.app.entity.helpers.TelephoneType" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/13/2015
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    pageContext.setAttribute("telephoneTypes", TelephoneType.values());
%>
<jsp:useBean id="telephoneTypes" scope="page" type="com.itechart.app.entity.helpers.TelephoneType[]"/>
<div class="modal fade" id="telephoneModal" tabindex="-1" role="dialog" aria-labelledby="telephoneModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close telephoneModal-close-modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="telephoneModalLabel">Telephone</h4>
                <input type="hidden" id="telephoneModal_id">
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">

                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-2">
                                <label class="" for="telephoneModal_country_code">Country</label>
                                <input type="text"  pattern="[0-9]{0,3}" class="form-control small-padding" id="telephoneModal_country_code" maxlength="3">
                            </div>
                            <div class="col-lg-2">
                                <label class="" for="telephoneModal_operator_code">Operator</label>
                                <input type="text" pattern="[0-9]{0,3}" class="form-control small-padding"  id="telephoneModal_operator_code" maxlength="3" >
                            </div>
                            <div class="col-lg-4">
                                <label class="" for="telephoneModal_number">Number</label>
                                <input type="tel" class="form-control" pattern="[0-9]{5,10}" id="telephoneModal_number" maxlength="15">
                            </div>
                            <div class="col-lg-4">
                                <label for="telephoneModal_type_select" class="">Telephone type</label>
                                <select class="form-control" name="telephoneTypes" id="telephoneModal_type_select">
                                    <c:forEach var="telephoneType" items="${telephoneTypes}">
                                        <option name="telephoneType" id="telephoneModal_type_${telephoneType}" value="${telephoneType}">${telephoneType.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-lg-12">
                                <label class="sr-only" for="telephoneModal_comment">Comment</label>
                                <input type="text" class="form-control" id="telephoneModal_comment" placeholder="Type any comment here" maxlength="100">
                            </div>
                        </div>
                    </div>

                    <div class="alert alert-danger" role="alert" id="telephoneModalAlert" style="display:none;">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        Enter a valid telephone N{0,3} N{0,3} N{5-10}
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default telephoneModal-close-modal">Close</button>
                <button type="button" class="btn btn-primary" id="telephoneModalApply" >Apply</button>
            </div>

        </div>
    </div>
</div>