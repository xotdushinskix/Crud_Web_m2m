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
    <title></title>
</head>
<body>
  <h3>User purchase:</h3>
  <table border="2">
    <thead>
    <tr>
      <th>User Id</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Shop Experience</th>
      <th>Bought Products</th>
    </tr>
    </thead>
    <tbody>
      <tr>
        <td><c:out value="${user.userId}" /></td>
        <td><c:out value="${user.firstName}" /></td>
        <td><c:out value="${user.lastName}" /></td>
        <td><c:out value="${user.shopExperience}" /></td>
        <td>
          <table border="1">
            <thead>
            <tr>
              <th>Brand</th>
              <th>Model</th>
              <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${userProducts}" var="productP">
              <tr>
                <td>${productP.product.productBrand}</td>
                <td>${productP.product.productModel}</td>
                <td>${productP.boughtQuantity}</td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </td>
      </tr>
    </tbody>
  </table>
</body>
</html>
