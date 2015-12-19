<jsp:useBean id="contact" scope="request" type="com.itechart.app.entity.Contact"/>
<%@ page import="com.itechart.app.entity.helpers.FamilyStatus" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/12/2015
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cl" uri="http://cloudinary.com/jsp/taglib" %>
<%
    pageContext.setAttribute("familyStatuses", FamilyStatus.values());
%>

<iframe id="hiddenAvatarFrame" name="hiddenAvatarFrame" hidden ></iframe>
<iframe id="hiddenAttachmentFrame" name="hiddenAttachmentFrame"hidden></iframe>
<div class="form-horizontal" role="form">
    <div class="form-group row">
        <div class="col-lg-push-2 col-lg-2">
            <c:choose>
                <c:when test="${contact.photoUrl != null}">
                    <img id="ava_image" src="${contact.photoUrl}" class="img-responsive" alt="${contact.fullName}">
                </c:when>
                <c:otherwise>
                    <img id="ava_image" src="http://res.cloudinary.com/goodcloud/image/upload/v1449943378/ContactBook/default_avatar.png" class="img-responsive" alt="${contact.fullName}">
                </c:otherwise>
            </c:choose>
            <input name="photoUrl" id="photoUrl" type="hidden">
        </div>
    </div>

    <div class="form-group">
        <label for="firstName" class="col-lg-2 control-label">First name</label>
        <div class="col-lg-10">
            <input type="text" name="firstName" class="form-control" id="firstName" placeholder="Albert" value="${contact.firstName}">
        </div>
    </div>

    <div class="form-group">
        <label for="lastName" class="col-lg-2 control-label">Last name</label>
        <div class="col-lg-10">
            <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Einstein" value="${contact.lastName}">
        </div>
    </div>

    <div class="form-group">
        <label for="patronymic" class="col-lg-2 control-label">Patronymic</label>
        <div class="col-lg-10">
            <input type="text" name="patronymic" class="form-control" id="patronymic" placeholder="Hermann" value="${contact.patronymic}">
        </div>
    </div>

    <div class="form-group">
        <label for="dateOfBirth" class="col-lg-2 control-label">Date of birth</label>
        <div class="col-lg-10">
            <input type="date" name="dateOfBirth" class="form-control" id="dateOfBirth" value="${contact.dateOfBirth}">
        </div>
    </div>

    <div class="form-group">
        <label for="sexIsMale" class="col-lg-2 control-label">Sex</label>
        <div class="col-lg-10">
            <select class="form-control" name="sexIsMale" id="sexIsMale">
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
        <label for="nationality" class="col-lg-2 control-label">Nationality</label>
        <div class="col-lg-10">
            <input type="text" name="nationality" class="form-control" id="nationality" placeholder="Kingdom of Württemberg" value="${contact.nationality}">
        </div>
    </div>

    <div class="form-group">
        <label for="familyStatus" class="col-lg-2 control-label">Family status</label>
        <div class="col-lg-10">
            <select class="form-control" name="familyStatus" id="familyStatus">
                <c:if test="${contact.familyStatus == null}">
                    <option value="">Choose status</option>
                </c:if>
                <c:forEach var="status" items="${familyStatuses}">
                    <c:choose>
                        <c:when test="${contact.familyStatus == status}">
                            <option value="${status}" selected>${status.value}</option>
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
        <label for="webSite" class="col-lg-2 control-label">Web site</label>
        <div class="col-lg-10">
            <input type="text" name="webSite" class="form-control" id="webSite" placeholder="www.einstein.com" value="${contact.webSite}">
        </div>
    </div>

    <div class="form-group">
        <label for="email" class="col-lg-2 control-label">Email</label>
        <div class="col-lg-10">
            <input type="email" name="email" class="form-control" id="email" placeholder="albert.einstein@gmail.com" value="${contact.email}">
        </div>
    </div>

    <div class="form-group">
        <label for="currentWorkplace" class="col-lg-2 control-label">Workplace</label>
        <div class="col-lg-10">
            <input type="text" name="currentWorkplace" class="form-control" id="currentWorkplace" placeholder="Company Inc." value="${contact.currentWorkplace}">
        </div>
    </div>

    <div class="form-group">
        <label class="col-lg-2 control-label">Location</label>
        <div class="col-lg-10">
            <div class="row">
                <div class="col-lg-4">
                    <label class="sr-only" for="postcode">Postcode</label>
                    <input type="text" name="postcode" class="form-control" id="postcode" placeholder="Postcode" value="${contact.postcode}">
                </div>
                <div class="col-lg-4">
                    <label class="sr-only" for="city">City</label>
                    <input type="text" name="city" class="form-control" id="city" placeholder="City" value="${contact.city}">
                </div>
                <div class="col-lg-4">
                    <label class="sr-only" for="country">Country</label>
                    <input type="text" name="country" class="form-control" id="country" placeholder="Country" value="${contact.country}">
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="address" class="col-lg-2 control-label">Address</label>
        <div id="address" class="col-lg-10">
            <div class="row">
                <div class="col-lg-4">
                    <label class="sr-only" for="street">Street</label>
                    <input type="text" name="street" class="form-control " id="street" placeholder="Street" value="${contact.street}">
                </div>
                <div class="col-lg-4">
                    <label class="sr-only" for="house">House</label>
                    <input type="text" name="house" class="form-control" id="house" placeholder="House" value="${contact.house}">
                </div>
                <div class="col-lg-4">
                    <label class="sr-only" for="apartment">Apartment</label>
                    <input type="text" name="apartment" class="form-control" id="apartment" placeholder="Apartment" value="${contact.apartment}">
                </div>
            </div>
        </div>
    </div>
</div>