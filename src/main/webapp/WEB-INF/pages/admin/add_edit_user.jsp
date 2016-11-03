<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/taglib.tld" prefix="custom" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add/Edit User page</title>
    <meta charset="UTF-8" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/form_validate.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/password_validate.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link href="${pageContext.servletContext.contextPath}/resources/css/inner_style.css" rel="stylesheet">
</head>
<body>
<spring:message code="login.placeholder" var="login_placeholder"/>
<spring:message code="password.placeholder" var="password_placeholder"/>
<spring:message code="password.again.placeholder" var="password_again_placeholder"/>
<spring:message code="firstName.placeholder" var="firstName_placeholder"/>
<spring:message code="lastName.placeholder" var="lastName_placeholder"/>
<spring:message code="email.placeholder" var="email_placeholder"/>
<spring:message code="birthday.placeholder" var="birthday_placeholder"/>
<spring:message code="cancel.button" var="cancel_button"/>
<spring:message code="add.user" var="add_user"/>
<spring:message code="edit.user" var="edit_user"/>

<c:choose>
    <c:when test="${action=='add'}">
        <c:set var="action" value="add"/>
        <c:set var="title" value="${add_user}"/>
        <c:set var="disabled" value=""/>
    </c:when>
    <c:when test="${action=='edit'}">
        <c:set var="action" value="edit"/>
        <c:set var="title" value="${edit_user}"/>
        <c:set var="disabled" value="true"/>
    </c:when>
</c:choose>

<div class="container">
    <h1 align = "left" style="font-size: 25px; margin-top: 100px;">${title}</h1>
    <form:form commandName="user" modelAttribute="user" action="${pageContext.servletContext.contextPath}/addEditUserDo/${action}" method="post"
          class="form-horizontal" role="form" id="registerform">

        <h1 align = "center" style="font-size: larger; margin-top: 10px; color: red;">${message}</h1>

        <div class="form-group"><spring:message code="login"/>*</div>
        <div class="form-group">
            <c:choose>
            <c:when test="${action=='edit'}">
                <form:input type="hidden" path="login" id="login"/>
                <form:input path="login" type="text" class="form-control" maxlength="50" style="width: 300px;"
                           disabled="true" tabindex="1" placeholder="${login_placeholder}" required="required" name="login"/></div>

            </c:when>
            <c:otherwise>
        <form:input path="login" type="text" class="form-control" maxlength="50" style="width: 300px;"
                    tabindex="1" placeholder="${login_placeholder}" required="required" name="login"/></div>
            </c:otherwise>
            </c:choose>

<form:errors path="login" cssStyle="color:red"/>

        <div class="form-group"><spring:message code="password"/>*</div>
        <div class="form-group">
        <form:password path="password" class="form-control" id="password"
                    name="password" placeholder="${password_placeholder}" required="required"
                    maxlength="50" style="width: 300px;"/></div>
        <span style="float: right" id="strengthValue"></span>


<form:errors path="password" cssStyle="color:red"/>



        <div class="form-group"><spring:message code="password.again"/>*</div>
        <div class="form-group">
        <form:password path="passwordagain" class="form-control" id="passwordagain"
                    name="password_again" placeholder="${password_again_placeholder}" required="required"
                                       maxlength="50" style="width: 300px;" onChange="checkPasswordMatch();"/></div>

        <div class = "registrationFormAlert" id="divCheckPasswordMatch" style="color:red;"></div>

<form:errors path="passwordagain" cssStyle="color:red"/>

        <div class="form-group"><spring:message code="email"/>*</div>
        <div class="form-group">
        <form:input path="email" class="form-control"
                    name="email" placeholder="${email_placeholder}" required="required"
                                       maxlength="50" style="width: 300px;"/></div>

<form:errors path="email" cssStyle="color:red"/>

        <div class="form-group"><spring:message code="name"/>*</div>
        <div class="form-group">
        <form:input path="firstName" type="text" class="form-control"
                    name="firstName" placeholder="${firstName_placeholder}" required="required"
                                       maxlength="50" style="width: 300px;"/></div>

<form:errors path="firstName" cssStyle="color:red"/>

        <div class="form-group"><spring:message code="surname"/>*</div>
        <div class="form-group">
        <form:input path="lastName" type="text" class="form-control"
                    name="lastName" placeholder="${lastName_placeholder}" required="required"
                                       maxlength="50" style="width: 300px;"/></div>

<form:errors path="lastName" cssStyle="color:red"/>

        <div class="form-group"><spring:message code="birthday"/>*</div>
        <div class="form-group">
            <form:input path="birthday" type="text" class="form-control" id="birthday"
                        name="birthday" placeholder="${birthday_placeholder}" required="required"
                                       maxlength="50" style="width: 300px;"/></div>

<form:errors path="birthday" cssStyle="color:red"/>

        <div class="form-group"><spring:message code="role"/>*</div>
        <div class="form-group">
            <form:select path="role" name="role" class="form-control" style="width: 300px; display: inline">
             <c:forEach var="role" items="${roles}">
                 <option value="${role.name}" name="role"
                            <c:if test="${role.id == roleById.id}">
                                  selected="selected"
                            </c:if>
                    >${role.name}
                </option>
            </c:forEach>
            </form:select>
        </div>
        <input type="submit" id="submitButton" class="btn btn-success" value="OK	" name="OK"
               style="margin-right: 10px; float: left;"/>
   </form:form>
    <a href="${pageContext.servletContext.contextPath}/admin">
        <input class="btn btn-danger" type="submit" value="${cancel_button}" name="cancel"></a>
</div>
</body>
</html>