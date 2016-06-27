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

  <form action="/make_purchase" method="post">
    Product ID : <input type="text" readonly="readonly" name="productIdForPurchase" value="<c:out value="${productId}" />" />
    <br/>
    Product Brand : <input type="text" readonly="readonly" name="productbrand" value="<c:out value="${productBrand}" />" />
    <br/>
    Product Model : <input type="text" readonly="readonly" name="productmodel" value="<c:out value="${productModel}" />" />
    <br/>
    Product Stock : <input type="text" readonly="readonly" name="productstock" value="<c:out value="${productStock}" />" />
    <br/>
    Product MPN : <input type="text" readonly="readonly" name="productmpn" value="<c:out value="${productMPN}" />" />
    <br/>
    Select purchase quantity : <input type="text" name="productStockForPurchase"/>
    <br/>
    <input type="submit" value="Make Purchase" name="makePurchase"/>
  </form>
  <br>
  <p style="position: absolute; top: 4%; right: 5.2%"><a href="products">Back To Main Page</a></p>
  <jsp:include page="logout.html" />
  <p style="position: absolute; top: 0%; right: 8%;"> Hello, <a href="user_page">
                                                                <c:out value="${sessionScope.firstName}"/></a></p>
  <p style="position: absolute; top: 8%; right: 6%"><a href="/cart">Back To Cart</a></p>

  <p style="position: absolute; top: 19%; left: 0.5%; color: rgba(53, 122, 31, 0.96); width: 100%"><c:out value="${messageToCart}"/></p>

</body>
</html>
