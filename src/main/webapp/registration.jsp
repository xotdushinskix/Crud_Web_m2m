<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 25.06.2016
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
  <form action="registration" method="post">
    First Name: <input type="text" name="firstname"><br> </br>
    Last Name: <input type="text" name="lastname"><br> </br>
    Shop Experience: <input type="text" name="shop_exp"><br> </br>
    Email: <input type="text" name="email_password"><br> </br>
    Password: <input type="password" name="password"><br> </br>
    <input type="submit" value="Registration">
  </form>
  <p style="position: absolute; top: 27%; left: 0.5%; color: firebrick"><c:out value="${messageEmpty}"/></p>
  <p style="position: absolute; top: 0; right: 1%; color: #343fb2"><a href="products">Back to main</a></p>
</body>
</html>
