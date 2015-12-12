<%@ page import="com.itechart.app.entity.helpers.FamilyStatus" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/12/2015
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cl" uri="http://cloudinary.com/jsp/taglib" %>
<%
    pageContext.setAttribute("familyStatuses", FamilyStatus.values());
%>
<form class="form-horizontal" id="data" method="POST" action="upload" enctype="multipart/form-data">
    <div class="row">
        <div class="col-md-2">
            <c:choose>
                <c:when test="${contact.photoUrl != ''}">
                    <img src="${contact.photoUrl}" class="img-responsive" alt="${contact.fullName}">
                </c:when>
                <c:otherwise>
                    <img src="http://res.cloudinary.com/goodcloud/image/upload/v1449943378/ContactBook/default_avatar.png" class="img-responsive" alt="${contact.fullName}">
                </c:otherwise>
            </c:choose>
            <%--TODO: image modal dialog--%>
        </div>

        <div class="col-md-10">

            <input type="hidden" id="${contact.id}">

            <div class="form-group">
                <label for="firstName" class="col-md-2 control-label">First name</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="firstName" placeholder="Albert" value="${contact.firstName}">
                </div>
            </div>

            <div class="form-group">
                <label for="lastName" class="col-md-2 control-label">Last name</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="lastName" placeholder="Einstein" value="${contact.firstName}">
                </div>
            </div>

            <div class="form-group">
                <label for="patronymic" class="col-md-2 control-label">Patronymic</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="patronymic" placeholder="Hermann" value="${contact.patronymic}">
                </div>
            </div>

            <div class="form-group">
                <label for="dateOfBirth" class="col-md-2 control-label"></label>
                <div class="col-md-10">
                    <input type="date" class="form-control" id="dateOfBirth" value="${contact.dateOfBirth}">
                </div>
            </div>

            <div class="form-group">
                <label for="sexIsMale" class="col-md-2 control-label">Sex</label>
                <div class="col-md-10">
                    <select name="sexIsMale" id="sexIsMale" form="data">
                        <c:choose>
                            <c:when test="${contact.sexIsMale != true}">
                                <option value="1">Male</option>
                                <option value="0" selected>Female</option>
                            </c:when>
                            <c:otherwise>
                                <option value="1" selected>Male</option>
                                <option value="0">Female</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="nationality" class="col-md-2 control-label">Nationality</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="nationality" placeholder="Kingdom of Württemberg" value="${contact.nationality}">
                </div>
            </div>

            <div class="form-group">
                <label for="familyStatus" class="col-md-2 control-label">Family status</label>
                <div class="col-md-10">
                    <select name="familyStatus" id="familyStatus" form="data">
                        <c:forEach var="status" items="${familyStatuses}">
                            <c:choose>
                                <c:when test="${contact.familyStatus == status}">
                                    <option value="${status} selected">${status.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status}">${status.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="webSite" class="col-md-2 control-label">Web site</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="webSite" placeholder="www.einstein.com" value="${contact.webSite}">
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="col-md-2 control-label">Email</label>
                <div class="col-md-10">
                    <input type="email" class="form-control" id="email" placeholder="albert.einstein@gmail.com" value="${contact.email}">
                </div>
            </div>

            <div class="form-group">
                <label for="currentWorkplace" class="col-md-2 control-label">Current workplace</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="currentWorkplace" placeholder="Company Inc." value="${contact.currentWorkplace}">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">Location</label>
                <div class="col-md-10">
                    <div class="form-inline">
                        <div class="form-group"><input type="text" class="form-control" id="postcode" placeholder="Postcode" value="${contact.postcode}"></div>
                        <div class="form-group"><input type="text" class="form-control" id="city" placeholder="City" value="${contact.city}"></div>
                        <div class="form-group"><input type="text" class="form-control" id="country" placeholder="Country" value="${contact.country}"></div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">Address</label>
                <div class="col-md-10">
                    <div class="form-inline">
                        <div class="form-group"><input type="text" class="form-control" id="street" placeholder="Street" value="${contact.street}"></div>
                        <div class="form-group"><input type="text" class="form-control" id="house" placeholder="House" value="${contact.house}"></div>
                        <div class="form-group"><input type="text" class="form-control" id="apartment" placeholder="Apartment" value="${contact.apartment}"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">

    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Submit</button>
        </div>
    </div>
</form>