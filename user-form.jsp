<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 01.05.2019
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserDao</title>
</head>
<body>
<div align="center">
    <h1>dao.UserDao</h1>
    <h2>
        <a href="new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="userList">List All Users</a>
    </h2>

</div>
<div align="center">
    <c:if test="${user != null}">
    <form action="edit" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="new" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit model.User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New model.User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user!=null}">
                    <input type="hidden" name="lastLogin"
                           value="<c:out value='${user.login}'/>"
                    />
                </c:if>
                <tr>
                    <th>Login</th>
                    <td>
                        <input type="text" name="login" size="45"
                               value="<c:out value='${user.login}'/>"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td>
                        <input type="text" name="password" size="15"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Role:</th>
                    <td>
                        <select name="roleId" size="1">
                            <option>user</option>
                            <option>admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
    </form>
</div>
</body>
</html>
