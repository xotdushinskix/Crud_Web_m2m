<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 21.05.2016
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Make Purchase</title>
</head>
<body>

  <form action="MakePurchase" method="post">
    Select customer id : <input type="text" name="userIDpurchase"/>
    <br/>
    Product ID : <input type="text" readonly="readonly" name="productIdForPurchase" value="<c:out value="${product.productId}" />" />
    <br/>
    Product Brand : <input type="text" readonly="readonly" name="productbrand" value="<c:out value="${product.productBrand}" />" />
    <br/>
    Product Model : <input type="text" readonly="readonly" name="productmodel" value="<c:out value="${product.productModel}" />" />
    <br/>
    Product Stock : <input type="text" readonly="readonly" name="productstock" value="<c:out value="${product.productStock}" />" />
    <br/>
    Product MPN : <input type="text" readonly="readonly" name="productmpn" value="<c:out value="${product.productMPN}" />" />
    <br/>
    Select purchase quantity : <input type="text" name="productStockForPurchase"/>
    <br/>
    <input type="submit" value="Make Purchase!" name="makePurchase"/>
  </form>
  <br>
  <td><a href="ShowAll?action=showAllUserAndProduct">Back To Main Page</a></td>
</body>
</html>
