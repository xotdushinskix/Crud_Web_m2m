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
      <title>Show purchase</title>
  </head>
  <body>
    <h3>User purchase:</h3>
      <table border="1">
        <thead>
        <tr>
          <th>Order Line</th>
          <th>Brand</th>
          <th>Model</th>
          <th>Quantity</th>
          <th>Change Quantity</th>
          <th>Delete Order</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${userProductsesNew}" var="userProd">
            <tr>
              <td>${userProd.userProductsId}</td>
              <td>${userProd.product.productBrand}</td>
              <td>${userProd.product.productModel}</td>
              <td>${userProd.boughtQuantity}</td>
              <td><a href="ShowAll?action=updatePurchQuantity&userProductsId=${userProd.userProductsId}">Update</a></td>
              <td><a href="ShowAll?action=deletePurchQuantity&userProductsId=${userProd.userProductsId}&userId=${userProd.user.userId}">Delete</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <br>
    <form action="FinalOrder" method="post">
      Your ID: <input readonly="readonly" name="userId" value="<c:out value="${userId}"/>" />
      <input type="submit" value="Finalize order" />
    </form>
    <br>
    <td><a href="ShowAll?action=showAllUserAndProduct">Back To Main Page</a></td>
  </body>
</html>
