<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 01.05.2019
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div align="center">
    <h1>Welcome, <c:out value="${login}"/> !</h1>
    <h2>It is your Page</h2>
    <a href="index.jsp">Back</a>
</div>
</body>
</html>
