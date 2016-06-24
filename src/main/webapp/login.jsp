<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 23.06.16
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form action="/products/login_error" method="post" style="position: fixed; top: 8%; left: 82%">
        Login: <input type="text" name="login"><br> </br>
        Password: <input type="password" name="password"><br> </br>
        <input type="submit" value="Enter"><br> </br>
    </form>
<br>
    <p style="position: fixed; top: 20%; left: 80%; color: firebrick;">${message}</p>
    <p style="position: fixed; top: 20%; left: 80%; color: firebrick;">${message3}</p>
</body>
</html>
