<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/18/2015
  Time: 6:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${currentPage!=null}">
        <div class="form-horizontal form-group">
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <div class="form-inline">
                        <ul class="pagination">
                            <c:forEach var="i" begin="1" end="${maxPageNumber}">
                                <c:choose>
                                    <c:when test="${i==currentPage}">
                                        <li class="active">
                                            <input class="btn btn-primary" type="submit" name="page" value="${i}">
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <input class="btn btn-default" type="submit" name="page" value="${i}">
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <li>

                                <select class="form-control" name="numberOfElements" id="numberOfElements" onchange="form.submit()">
                                    <c:choose>
                                        <c:when test="${numberOfElements==5}">
                                            <option value="5" selected>5</option>
                                            <option value="10">10</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="5">5</option>
                                            <option value="10" selected>10</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
</c:choose>
