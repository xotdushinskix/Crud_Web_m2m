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


  <%--<h3>Select your customer</h3>--%>
  <%--<form action="MakePurchase" method="get">--%>
    <%--User ID : <input type="text" name="userIDpurchase"/>--%>
    <%--&lt;%&ndash;User Last Name : <input type="text" name="userLastNpurchase"/>&ndash;%&gt;--%>
    <%--<br/>--%>
    <%--<h3>All products list:</h3>--%>
    <%--<table border="2">--%>
      <%--<thead>--%>
      <%--<tr>--%>
        <%--<th>Product Id</th>--%>
        <%--<th>Brand</th>--%>
        <%--<th>Model</th>--%>
        <%--<th>Stock</th>--%>
        <%--<th>MPN</th>--%>
        <%--<th>Add to Cart</th>--%>
      <%--</tr>--%>
      <%--</thead>--%>
      <%--<tbody>--%>
      <%--<c:forEach items="${products}" var="product1">--%>
        <%--<tr>--%>
          <%--<td>--%>
            <%--<input type="text" readonly="readonly" name="productIdforPurchase" value="<c:out value="${product1.productId}" />" />--%>
          <%--</td>--%>
          <%--<td>${product1.productBrand}</td>--%>
          <%--<td>${product1.productModel}</td>--%>
          <%--<td>--%>
            <%--<input type="text" readonly="readonly" name="productStockforPurchase" value="<c:out value="${product1.productStock}" />" />--%>
          <%--</td>--%>
          <%--<td>${product1.productMPN}</td>--%>
          <%--<td><input type="text" name="productQuantityToCart"/></td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
      <%--</tbody>--%>
    <%--</table>--%>
    <%--<input type="submit" value="Make Purchase!" />--%>
  <%--</form>--%>
</body>
</html>
