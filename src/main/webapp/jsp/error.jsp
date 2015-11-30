<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 11/25/2015
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8"%>
<html><title>Error Page</title>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
Message from exception: ${pageContext.exception.message}
</body>
</html>
