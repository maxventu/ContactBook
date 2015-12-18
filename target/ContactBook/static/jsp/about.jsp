<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/15/2015
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>About</title>
    </head>
<body>
<c:import url="${pageContext.request.contextPath}/static/jsp/partial/_navbar.jsp"/>
<div class="container">
    <div class="form-horizontal">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-8">
                <h3>
                    Created by <a href="https://www.linkedin.com/profile/view?id=AAIAABsjiI8B5qsJ1HnCcI9ajXz4GuD_b4EAHUQ">Kalenik Maxim</a>, 2015
                </h3>
            </div>
        </div>
    </div>
</div>
</body>
</html>