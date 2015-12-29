<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/10/2015
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="contacts" scope="request" type="java.util.List<com.itechart.app.entity.Contact>"/>
<jsp:useBean id="templates" scope="request" type="java.util.List<com.itechart.app.entity.helpers.Template >"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="static/css/contactbook.css"/>
    <title>Email</title>
</head>
<body>
<c:import url="partial/_navbar.jsp"/>
<div class="container">
    <div class="form-horizontal">

        <form action="email" method="post">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-8">
                <h2>
                    Emails for:
                    <c:forEach var="contact" items="${contacts}" varStatus="i">
                        <c:if test="${i.index != 0}">, </c:if>
                        <input hidden name="contact_id" value="${contact.id}">
                        <a href="edit?id=${contact.id}"> <input type="button" class="btn btn-default btn-xs" value="${contact.email}"></a>
                    </c:forEach>
                </h2>
            </div>
        </div>
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-lg-6">
                                    <input type="text" class="form-control" name="subject" placeholder="Type any subject here" value="">
                                </div>
                                <div class="col-lg-4">
                                        <select class="form-control" name="template" id="template_select">
                                            <c:forEach var="template" items="${templates}">
                                                <option value="${template.id}">${template.name}</option>
                                            </c:forEach>
                                        </select>
                                </div>
                                <div class="col-lg-2">
                                    <button type="submit" class="btn btn-info">Submit</button>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <textarea disabled name="structure" class="form-control" rows="8" id="template_text" placeholder="You can choose template to get smth here"></textarea>
                            </div>
                            <div class="row">
                                <textarea name="message" class="form-control" rows="8"  placeholder="Type any message here"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<span hidden>
    <c:forEach var="template" items="${templates}">
        <input type="hidden" id="template_text_${template.id}" value="${template.structure}">
    </c:forEach>
</span>
</body>
<script src="static/js/emailForm.js"></script>
</html>
