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
    <form action="EditProdQuantityInPO" method="post">
        Order ID : <input type="text" readonly="readonly" name="userProductsId" value="<c:out value="${userProductsId}" />" />
        <br/>
        User ID : <input type="text" readonly="readonly" name="userId" value="<c:out value="${userId}" />" />
        <br/>
        Product ID : <input type="text" readonly="readonly" name="productIdEditQuantityPurchase" value="<c:out value="${product.productId}" />" />
        <br/>
        Product Brand : <input type="text" readonly="readonly" name="productbrand" value="<c:out value="${product.productBrand}" />" />
        <br/>
        Product Model : <input type="text" readonly="readonly" name="productmodel" value="<c:out value="${product.productModel}" />" />
        <br/>
        Product Stock : <input type="text" readonly="readonly" name="productstock" value="<c:out value="${product.productStock}" />" />
        <br/>
        Product Purchase Quantity : <input type="text" readonly="readonly" name="productPurchQuantity" value="<c:out value="${prodQuantityInOrderBeforeEdit}" />" />
        Enter New Quantity : <input type="text" name="productPurchQuantityForEdit" value="<c:out value="${product.productStock}" />" />
        <br/>
        <input type="submit" value="Edit Qunatity" name="EditProdQuantityInPO"/>
    </form>
</body>
</html>
