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
            Change Password for ${user.username}
        </h3>

        <form:form action="saveNewPassword" modelAttribute="user" method="post">

            <!-- need to associate this data with user id
            ENABLES UPDATING -->
            <form:hidden path="id"/>
            <form:hidden path="username"/>
            <form:hidden path="currentPasswordHash"/>
            <form:hidden path="editingPassword"/>

            <table>
                <tbody>
                <tr>
                    <td><label>Current Password:</label></td>
                    <td>
                        <form:password path="currentPassword"/>
                        <form:errors path="currentPassword" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td><label>New Password:</label></td>
                    <td>
                        <form:password path="password"/>
                        <form:errors path="password" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td><label>Confirm Password:</label></td>
                    <td>
                        <form:password path="passwordConfirm"/>
                        <form:errors path="passwordConfirm" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Change" class="save"/></td>
                </tr>
                </tbody>
            </table>
        </form:form>

        <c:url var="returnLink" value="/user/edit">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <div style="clear; both;"></div>
        <p>
            <a href="${returnLink}">Back</a>
        </p>
    </div>
</div>
</body>
</html>
