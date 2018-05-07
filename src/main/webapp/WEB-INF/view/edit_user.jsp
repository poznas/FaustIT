<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Form</title>
    <link href="<c:url value="/static/styles/style.css" />" rel="stylesheet">
    <link href="<c:url value="/static/styles/form_style.css" />" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>User Manager</h2>
    </div>

    <div id="container">
        <h3>
            Save User
        </h3>

        <form:form action="saveUser" modelAttribute="user" method="post">

            <!-- need to associate this data with user id
            ENABLES UPDATING -->
            <form:hidden path="id"/>
            <form:hidden path="editingPassword"/>

            <table>
                <tbody>
                <tr>
                    <td><label class="formLabel">Username:</label></td>
                    <td>
                        <form:input path="username"/>
                        <form:errors path="username" cssClass="error"/>
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${user.editingPassword}">
                        <tr>
                            <td><label class="formLabel">Password:</label></td>
                            <td>
                                <form:password path="password"/>
                                <form:errors path="password" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="formLabel">Confirm Password:</label></td>
                            <td>
                                <form:password path="passwordConfirm"/>
                                <form:errors path="passwordConfirm" cssClass="error"/>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="password"/>
                    </c:otherwise>
                </c:choose>

                <tr>
                    <td><label class="formLabel">First name:</label></td>
                    <td>
                        <form:input path="firstName"/>
                        <form:errors path="firstName" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td><label class="formLabel">Last name:</label></td>
                    <td>
                        <form:input path="lastName"/>
                        <form:errors path="lastName" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td><label class="formLabel">Date of Birth:</label></td>
                    <td><form:input path="birthDate" type="date"/></td>
                </tr>
                <tr>
                    <td><label class="formLabel">Groups:</label></td>
                    <td>
                        <form:checkboxes path="groupNames" items="${existingGroups}" cssClass="checkboxes"/>
                    </td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Save" class="save"/></td>
                </tr>
                </tbody>
            </table>
        </form:form>

        <c:choose>
            <c:when test="${!user.editingPassword}">
                <c:url var="changePasswordLink" value="/user/changePassword">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <input type="button" value="Change Password"
                       onclick="window.location.href='${changePasswordLink}'; return false;"
                       class="add-button">
            </c:when>
        </c:choose>

        <div style="clear; both;"></div>
        <p>
            <a href="${pageContext.request.contextPath}/user/list">Back to list</a>
        </p>
    </div>
</div>
</body>
</html>
