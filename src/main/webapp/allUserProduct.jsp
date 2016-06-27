<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 10.05.16
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>

<h3>All products list:</h3>
<table border="2">
    <thead>
    <tr>
        <th>Product Id</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Stock</th>
        <th>MPN</th>
        <th>Add to cart</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product1">
        <tr>
            <td>${product1.productId}</td>
            <td>${product1.productBrand}</td>
            <td>${product1.productModel}</td>
            <td>${product1.productStock}</td>
            <td>${product1.productMPN}</td>
            <td><a href="make_purchase?productId=<c:out value="${product1.productId}"/>">To cart</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${sessionScope.userLogin != null}" > <jsp:include page="logout.html" /> </c:if>

<p style="position: absolute; top: 0%; right: 15%;"><c:if test="${sessionScope.userLogin == null}" > Hello, Guest </c:if></p>
<p><c:if test="${sessionScope.userLogin == null}" > <%@include file="/login.jsp" %> </c:if></p>

<p style="position: absolute; top: 0%; right: 8%;"> <c:if test="${sessionScope.userLogin != null}" > Hello,
                                        <a href="user_page"><c:out value="${sessionScope.userName}"/></a></c:if></p>

<p style="position: absolute; top: 3%; right: 8%;"> <c:if test="${sessionScope.userLogin != null}" >
    <a href="cart">Cart</a></c:if></p>

<p style="position: absolute; top: 0%; right: 8%;"> <c:if test="${sessionScope.userLogin == null}" >
    <a href="registration">Registration</a></c:if></p>

</body>
</html>
