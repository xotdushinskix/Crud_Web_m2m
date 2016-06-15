<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 06.06.2016
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
  <h3>Order:</h3>
  Thanks for purchase! Your order id is : <c:out value="${orderId}"/>
  <br>
  <td><a href="ShowAll?action=showAllUserAndProduct">Back To Main Page</a></td>
</body>
</html>
