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
    <form action="finalize_order" method="post">
      <table border="1">
        <thead>
        <tr>
          <th>Order Line</th>
          <th>Brand</th>
          <th>Model</th>
          <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${userProductsesNew}" var="userProd">
            <tr>
              <%--<td>${userProd.userProductsId}</td>--%>
              <td><input type="text" readonly="readonly" name="orderLineId" value="<c:out value="${userProd.userProductsId}"/>" /></td>
              <td>${userProd.product.productBrand}</td>
              <td>${userProd.product.productModel}</td>
              <td><input type="text" name="b_quantity" value="<c:out value="${userProd.boughtQuantity}"/>" /></td>
              <td><input type="submit" value="Update quantity" name="update_quantity"/></td>
              <td><input type="submit" value="Delete line" /></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <br>
      <input type="submit" value="Finalize order" name="final_order"/>
    </form>
    <br>
    <p style="position: absolute; top: 4%; right: 1%"><a href="products">Back To Main Page</a></p>
    <jsp:include page="logout.html" />
  </body>
</html>
