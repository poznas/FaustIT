<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Group List</title>
    <link href="<c:url value="/static/styles/style.css" />" rel="stylesheet">

</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Group Manager</h2>
    </div>

    <input type="button" value="Add Group"
           onclick="window.location.href='new'; return false;"
           class="add-button">
    <table>
        <tr>
            <th>Group Name</th>
            <th>Users</th>
            <th>Action</th>
        </tr>
        <c:forEach var="group" items="${groups}">

            <c:url var="updateLink" value="/group/edit">
                <c:param name="groupId" value="${group.id}"/>
            </c:url>
            <c:url var="deleteLink" value="/group/delete">
                <c:param name="groupId" value="${group.id}"/>
            </c:url>
            <tr>
                <td>${group.groupName}</td>
                <td>${group.userNames}</td>
                <td>
                    <a href="${updateLink}">Edit</a>
                    |
                    <a href="${deleteLink}"
                       onclick="if (!(confirm('Are you sure you want to delete this group?')))
                                            return false">
                        Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" value="User List"
           onclick="window.location.href='/user/list'; return false;"
           class="add-button">
</div>
</body>
</html>
