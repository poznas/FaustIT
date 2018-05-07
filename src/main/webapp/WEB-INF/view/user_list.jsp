<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
    <link href="<c:url value="/static/styles/style.css" />" rel="stylesheet">

</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>User Manager</h2>
    </div>

    <input type="button" value="Add User"
           onclick="window.location.href='new'; return false;"
           class="add-button">
    <table>
        <tr>
            <th>Username</th>
            <th>Groups</th>
            <th>Full Name</th>
            <th>Birth Date</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${users}">

            <c:url var="updateLink" value="/user/edit">
                <c:param name="userId" value="${user.id}"/>
            </c:url>
            <c:url var="deleteLink" value="/user/delete">
                <c:param name="userId" value="${user.id}"/>
            </c:url>
            <tr>
                <td>${user.username}</td>
                <td>${user.groupNames}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.birthDate}</td>
                <td>
                    <a href="${updateLink}">Edit</a>
                    |
                    <a href="${deleteLink}"
                       onclick="if (!(confirm('Are you sure you want to delete this user?')))
                                            return false">
                        Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" value="Group List"
           onclick="window.location.href='/group/list'; return false;"
           class="add-button">
</div>
</body>
</html>
