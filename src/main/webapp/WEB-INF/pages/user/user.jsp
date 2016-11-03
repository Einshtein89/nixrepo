<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User page</title>
    <meta charset="UTF-8" />
    <link href="${pageContext.servletContext.contextPath}/resources/css/inner_style.css" rel="stylesheet">
    </head>
<body>
<h1 align = "center" style="font-size: 25px; margin-top: 150px;"><spring:message code="hello"/>, ${user.firstName}!</h1>
<br>
<br>
<br>
<form id="logoutForm" method="post" action="${pageContext.servletContext.contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
</form>
<div align="center">
<spring:message code="click.here.to"/> <a onclick="document.forms['logoutForm'].submit()" style="cursor: pointer; text-decoration: underline">
<spring:message code="logout"/></a></div>
</body>
</html>
