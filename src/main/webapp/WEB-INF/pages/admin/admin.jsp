<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/taglib.tld" prefix="custom" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Admin page</title>
    <meta charset="UTF-8" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link href="${pageContext.servletContext.contextPath}/resources/css/inner_style.css" rel="stylesheet">

    </head>
<body>
<div class="container">
    <div align="right">
        <spring:message code="admin"/> ${user.lastName}
        <form id="logoutForm" method="post" action="${pageContext.servletContext.contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        </form>
        <a onclick="document.forms['logoutForm'].submit()" style="cursor: pointer"><spring:message code="logout"/></a>
    </div>
<h1 align = "center" style="font-size: 25px; margin-top: 150px;"><spring:message code="hello"/>, ${user.firstName}!</h1>
    <h1 align = "center" style="font-size: larger; margin-top: 10px; color: red;">${message}</h1>
    <div align="left">
        <form class="form-inline" role="form" action="${pageContext.servletContext.contextPath}/addUser/add" method="GET">
            <input type="submit" class="btn btn-primary" value="<spring:message code="add.user"/>">
        </form>
    </div>
<br>
<br>
<br>
    <c:if test="${fn:length(users) ne 0}">
    <div class="container_1">
     <table class="table table-striped">
     <thead><td><spring:message code="login"/></td>
     <td><spring:message code="firstName.admin"/></td>
     <td><spring:message code="lastName.admin"/></td>
     <td><spring:message code="age"/></td>
     <td><spring:message code="role"/></td>
     <td><spring:message code="actions"/></td>
     </thead><tbody>
<custom:userstable users="${users}" roles="${roles}"/>
    </div>
        <div class =".show" align="center"></div>
    </c:if>
</div>
</body>
</html>