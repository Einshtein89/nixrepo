<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome page</title>
    <meta charset="UTF-8" />
    <link href="${pageContext.servletContext.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<h1 align = "center" style="font-size: 25px; margin-top: 150px;">
<spring:message code="welcome"/></h1>
<c:if test="${param.error != null}">
    <h1 align = "center" style="font-size: larger; margin-top: 10px; color: red;">
        <p><spring:message code="wrong.username.message"/></p>
    </h1>

</c:if>
<c:if test="${param.logout != null}">
    <h1 align = "center" style="font-size: larger; margin-top: 10px; color: red;">
        <p><spring:message code="logout.message"/></p>
    </h1>
</c:if>
<h1 align = "center" style="font-size: larger; margin-top: 10px; color: red;">${message}</h1>

<form class="box login" action="${pageContext.servletContext.contextPath}/login" method="post">
<span style="float: right">
    <a href="?lang=en">en</a> 
    | 
    <a href="?lang=ru">ru</a>
</span>
    <fieldset class="boxBody">
        <label for="login">
           <spring:message code="login"/>
            <input path="login" type="text" tabindex="1" placeholder="<spring:message code="login"/>" name="login" required="required" id="login"
                        style="background: url(${pageContext.servletContext.contextPath}/resources/images/user.png)
        no-repeat 5px; padding-left: 40px; width: 236px;"/></label>
        <label for="password">
             <spring:message code="password"/>
            <input type="password" tabindex="2" placeholder="<spring:message code="password"/>" name="password" required="required" id="password"
                           style="background: url(${pageContext.servletContext.contextPath}/resources/images/password.png)
        no-repeat 5px; padding-left: 40px; width: 236px;"/></label>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    </fieldset>
    <footer>
        <input type="submit" class="btnLogin" value="<spring:message code="loginButton"/>" tabindex="4">
        <a href="${pageContext.servletContext.contextPath}/register/"
           style="margin-right: 69px; color:white; text-decoration: none;" class="btnLogin" ><spring:message code="register"/></a>

    </footer>
</form>
</body>
</html>

