<%--
  Created by IntelliJ IDEA.
  User: FromxSoul
  Date: 22.05.2016
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
      <title>Cart</title>
  </head>
  <body>
    <h3>User purchase:</h3>
      <table border="1">
        <thead>
        <tr>
          <th>Order Line Id</th>
          <th>Brand</th>
          <th>Model</th>
          <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${userProductsesNew}" var="userProd">
            <tr>
              <td>${userProd.userProductsId}</td>
              <td>${userProd.product.productBrand}</td>
              <td>${userProd.product.productModel}</td>
              <td>${userProd.boughtQuantity}</td>
              <td><a href="cart/change?action=update&order_line_id=<c:out value="${userProd.userProductsId}"/>">Update</a></td>
              <td><a href="cart/change?action=delete&order_line_id=<c:out value="${userProd.userProductsId}"/>">Delete</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

    <br>
    <form action="finalize_order" method="post">
      <input type="submit" value="Finalize order" name="final_order"/>
    </form>


      <h3>Order History</h3>
      <table border="1">
        <thead>
          <tr>
            <th>Order Id</th>
            <th>Created Date</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${userProductsOrder}" var="userProdOrder">
            <tr>
              <td>${userProdOrder.order.orderId}</td>
              <td>${userProdOrder.order.currentData}</td>
              <td><a href="cart/order_lines?orderId=<c:out value="${userProdOrder.order.orderId}"/>">Show Lines</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>


    <br>
    <p style="position: absolute; top: 4%; right: 1%"><a href="products">Back To Main Page</a></p>
    <jsp:include page="logout.html" />

    <p style="position: absolute; top: 0%; right: 8%;"> <c:if test="${sessionScope.userLogin != null}" > Hello,
      <a href="user_page"><c:out value="${sessionScope.userName}"/></a></c:if></p>

  </body>
</html>
