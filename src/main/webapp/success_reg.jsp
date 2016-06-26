<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 25.06.2016
  Time: 17:03
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
 Hi, <c:out value="${firstName}"/>! You are successfully registered. Please, <a href="/products">login</a> again.
</body>
</html>
