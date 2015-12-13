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
<div class="modal fade" id="telephoneModal" tabindex="-1" role="dialog" aria-labelledby="telephoneModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="telephoneModalLabel">Telephone of</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-1">
                        <label class="sr-only" for="telephoneModal_country_code">Country code</label>
                        <input type="text" class="form-control " id="telephoneModal_country_code" placeholder="Contry code" value="">
                    </div>
                    <div class="col-lg-1"  style="padding-left: 0;">
                        <label class="sr-only" for="telephoneModal_operator_code">Operator code</label>
                        <input type="text" class="form-control " id="telephoneModal_operator_code" placeholder="Operator code" value="">
                    </div>
                    <div class="col-lg-4"  style="padding-left: 0;">
                        <label class="sr-only" for="telephoneModal_number">Number</label>
                        <input type="text" class="form-control" id="telephoneModal_number" placeholder="Number" value="">
                    </div>
                    <div class="col-lg-4"  style="padding-left: 0;">
                        <label for="telephoneModal_type" class="sr-only">Telephone type</label>
                        <select class="form-control" name="telephoneType" id="telephoneModal_type">
                            <c:forEach var="telephoneType" items="${telephoneTypes}">
                                <option value="${status}">${status.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <label class="sr-only" for="telephoneModal_comment">Comment</label>
                        <input type="text" class="form-control" id="telephoneModal_comment" placeholder="Type any comment here" value="">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="telephoneModalApply">Apply</button>
            </div>
        </div>
    </div>
</div>
