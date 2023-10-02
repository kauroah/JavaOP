<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1>List of Users</h1>
<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>

    </tr>

    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.uname}</td>
            <td>${user.password}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
