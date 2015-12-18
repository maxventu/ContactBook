<%@ page import="com.itechart.app.entity.helpers.FamilyStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("familyStatuses", FamilyStatus.values());
%>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/10/2015
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<c:import url="partial/_navbar.jsp"/>
<form action="/ContactBook/search" method="post">
    <div class="container">
        <div class="form-horizontal">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-8">
                    <div class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="firstName" class="col-lg-2 control-label">First name</label>
                            <div class="col-lg-10">
                                <input type="text" name="firstName" class="form-control" id="firstName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="lastName" class="col-lg-2 control-label">Last name</label>
                            <div class="col-lg-10">
                                <input type="text" name="lastName" class="form-control" id="lastName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="patronymic" class="col-lg-2 control-label">Patronymic</label>
                            <div class="col-lg-10">
                                <input type="text" name="patronymic" class="form-control" id="patronymic">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="dateOfBirth" class="col-lg-2 control-label">Date of birth</label>
                            <div class="col-lg-2">
                                <select class="form-control" name="dateIsGreater" id="dateIsGreater">
                                    <option value="1" selected>&gt;</option>
                                    <option value="0">&lt;</option>
                                </select>
                            </div>
                            <div class="col-lg-8">
                                <input type="date" name="dateOfBirth" class="form-control" id="dateOfBirth">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="sexIsMale" class="col-lg-2 control-label">Sex</label>
                            <div class="col-lg-10">
                                <select class="form-control" name="sexIsMale" id="sexIsMale">
                                    <option value=""></option>
                                    <option value="1">Male</option>
                                    <option value="0">Female</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="nationality" class="col-lg-2 control-label">Nationality</label>
                            <div class="col-lg-10">
                                <input type="text" name="nationality" class="form-control" id="nationality" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="familyStatus" class="col-lg-2 control-label">Family status</label>
                            <div class="col-lg-10">
                                <select class="form-control" name="familyStatus" id="familyStatus">
                                    <option value=""></option>
                                    <c:forEach var="status" items="${familyStatuses}">
                                        <option value="${status}">${status.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="currentWorkplace" class="col-lg-2 control-label">Workplace</label>
                            <div class="col-lg-10">
                                <input type="text" name="currentWorkplace" class="form-control" id="currentWorkplace">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-2 control-label">Location</label>
                            <div class="col-lg-10">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <label class="sr-only" for="postcode">Postcode</label>
                                        <input type="text" name="postcode" class="form-control" id="postcode" placeholder="Postcode">
                                    </div>
                                    <div class="col-lg-4">
                                        <label class="sr-only" for="city">City</label>
                                        <input type="text" name="city" class="form-control" id="city" placeholder="City">
                                    </div>
                                    <div class="col-lg-4">
                                        <label class="sr-only" for="country">Country</label>
                                        <input type="text" name="country" class="form-control" id="country" placeholder="Country" >
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
                                        <input type="text" name="street" class="form-control " id="street" placeholder="Street">
                                    </div>
                                    <div class="col-lg-4">
                                        <label class="sr-only" for="house">House</label>
                                        <input type="text" name="house" class="form-control" id="house" placeholder="House">
                                    </div>
                                    <div class="col-lg-4">
                                        <label class="sr-only" for="apartment">Apartment</label>
                                        <input type="text" name="apartment" class="form-control" id="apartment" placeholder="Apartment" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-10">
                    <button type="submit" name="searchBtn" value="search" class="btn btn-default">Search</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
