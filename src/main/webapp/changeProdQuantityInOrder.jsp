<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 30.05.16
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/cart/change" method="post">
        Order ID : <input type="text" readonly="readonly" name="orderLineId" value="<c:out value="${orderLineId}" />" />
        <br/>
        Product ID : <input type="text" readonly="readonly" name="productIdEditQuantityPurchase" value="<c:out value="${productId}" />" />
        <br/>
        Product Brand : <input type="text" readonly="readonly" name="productBrand" value="<c:out value="${productBrand}" />" />
        <br/>
        Product Model : <input type="text" readonly="readonly" name="productModel" value="<c:out value="${productModel}" />" />
        <br/>
        Product Stock : <input type="text" readonly="readonly" name="productStock" value="<c:out value="${productStock}" />" />
        <br/>
        Product Purchase Quantity : <input type="text" readonly="readonly" name="quantityBeforeEdit" value="<c:out value="${quantityBeforeEdit}" />" />
        Enter New Quantity : <input type="text" name="productPurchQuantityForEdit"  />
        <br/>
        <input type="submit" value="Edit Qunatity" name="EditProdQuantityInPO"/>
    </form>
    <p style="position: absolute; top: 4%; right: 6%"><a href="/products">Back To Main Page</a></p>
    <p style="position: absolute; top: 8%; right: 6%"><a href="/cart">Back To Cart</a></p>
    <jsp:include page="logout.html" />
    <p style="position: fixed; top: 16%; left: 0; color: #20b21d;">${message}</p>

    <p style="position: absolute; top: 0%; right: 6%;"> <c:if test="${sessionScope.userLogin != null}" > Hello,
        <a href="/user_page"><c:out value="${sessionScope.userName}"/></a></c:if></p>
</body>
</html>
