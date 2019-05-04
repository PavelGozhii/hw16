<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 01.05.2019
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserDao</title>
</head>
<body>
<div style="text-align: center;">
    <h1>UserDao</h1>
    <h2>
        <a href="new">Add New User</a>
        &nbsp; &nbsp; &nbsp;
        <a href="userList">List All Users</a>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>Login</th>
            <th>Password</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.roleId}"/></td>
                <td>
                    <a href="edit?login=<c:out value='${user.login}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?login=<c:out value='${user.login}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>