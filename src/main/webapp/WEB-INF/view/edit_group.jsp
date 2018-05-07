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
        <h2>Group Manager</h2>
    </div>

    <div id="container">
        <h3>
            Save Group
        </h3>

        <form:form action="saveGroup" modelAttribute="group" method="post">

            <!-- need to associate this data with group id
            ENABLES UPDATING -->
            <form:hidden path="id"/>

            <table>
                <tbody>
                <tr>
                    <td><label class="formLabel">Group Name:</label></td>
                    <td><form:input path="groupName"/>
                        <form:errors path="groupName" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label class="formLabel">Users:</label></td>
                    <td>
                        <form:checkboxes path="userNames" items="${existingUsers}" cssClass="checkboxes"/>
                    </td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Save" class="save"/></td>
                </tr>
                </tbody>
            </table>
        </form:form>
        <div style="clear; both;"></div>
        <p>
            <a href="${pageContext.request.contextPath}/group/list">Back to list</a>
        </p>
    </div>
</div>
</body>
</html>
