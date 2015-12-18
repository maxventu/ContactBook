<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contacts" scope="request" type="java.util.List<com.itechart.app.entity.Contact>"/>
<jsp:useBean id="templates" scope="request" type="java.util.List<com.itechart.app.entity.helpers.EmailTemplate >"/>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/10/2015
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email</title>
</head>
<body>
<c:import url="partial/_navbar.jsp"/>
<div class="container">
    <div class="form-horizontal">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-8">
                <h2>
                    <c:forEach var="contact" items="${contacts}" varStatus="i">
                        <c:if test="${i.index != 0}">, </c:if>
                        <a href="edit?id=${contact.id}"> <button type="button" class="btn btn-default btn-xs">${contact.email}</button></a>
                    </c:forEach>
                </h2>
            </div>
        </div>
        <form action="email" method="post">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-8">

                    <input type="text" size="50" name="subject" placeholder="Type any subject here" value="">
                    <input type="text" size="50" name="message" placeholder="Type any message here" value="">
                    <input type="submit" name="templateId" value="simple" class="btn" />
                </div>
            </div>
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-8">
                    <c:forEach var="template" items="${templates}">
                        <p class="subject">Subject: <b>${template.value.subject}</b></p>
                        ${template.value.template}
                        <c:if test="${template.value.requiresSubstitution}">
                            <c:forEach var="substitution" items="${template.value.substitutions}">
                                <p><input type="text" name="input${template.key}" value="${substitution}" /></p>
                            </c:forEach>
                        </c:if>
                        <input type="submit" name="templateId" value="${template.key}" class="btn" />
                    </c:forEach>

                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-8">
                <h3>
                    <a href="main"><button class="btn btn-default">Home</button></a>
                </h3>
            </div>
        </div>
    </div>
</div>
</body>
</html>
