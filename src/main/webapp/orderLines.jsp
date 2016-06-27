<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 27.06.16
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order Lines</title>
</head>
<body>
<h3>Order lines:</h3>
<table border="2">
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Bought Quantity</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orderLines}" var="orderLine">
        <tr>
            <td>${orderLine.product.productId}</td>
            <td>${orderLine.product.productBrand}</td>
            <td>${orderLine.product.productModel}</td>
            <td>${orderLine.boughtQuantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p style="position: absolute; top: 4%; right: 6%"><a href="/products">Back To Main Page</a></p>
<p style="position: absolute; top: 8%; right: 6%"><a href="/cart">Back To Cart</a></p>
<jsp:include page="logout.html" />

<p style="position: absolute; top: 0%; right: 6%;"> <c:if test="${sessionScope.userLogin != null}" > Hello,
    <a href="/user_page"><c:out value="${sessionScope.userName}"/></a></c:if></p>
</body>
</html>
