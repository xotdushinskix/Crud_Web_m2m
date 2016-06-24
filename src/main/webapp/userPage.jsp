<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 24.06.16
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
    First Name:  <form action="/user_edit" method="post">
                    <input type="text" name="newFirstName" value="<c:out value="${firstName}"/>"> <br>
                    <input type="submit" value="Change" name="newFN"> <br>
                  </form> <br>
     <p style="position: absolute; top: 6%; left: 0.5%; color: firebrick"><c:out value="${message}"/></p>
     <p style="position: absolute; top: 6%; left: 0.5%; color: firebrick"><c:out value="${messageEFN}"/></p>
    Last Name:  <form action="/user_edit" method="post">
                    <input type="text" name="newLastName" value="<c:out value="${secondName}"/>"> <br>
                    <input type="submit" value="Change" name="newLN"> <br>
                </form> <br>
    <p style="position: absolute; top: 16%; left: 0.5%; color: firebrick"><c:out value="${message1}"/></p>
    Shop Experience:  <form action="/user_edit" method="post">
                          <input type="text" name="newShopExperience" value="<c:out value="${userShopExperience}"/>"> <br>
                          <input type="submit" value="Change" name="newSE"> <br>
                        </form> <br>
    <p style="position: absolute; top: 26%; left: 0.5%; color: firebrick"><c:out value="${message2}"/></p>
     <form action="/user_edit" method="post">
         Enter your current password: <input type="password" name="currentPassword"> <br>
         Enter new password: <input type="password" name="newPassword"> <br>
         Confirm new password: <input type="password" name="confirmNewPassword"> <br>
         <input type="submit" value="Change" name="newPassword"> <br>
     </form>
    <p style="position: absolute; top: 40%; left: 0.5%; color: firebrick"><c:out value="${message3}"/></p>
    <p style="position: absolute; top: 31%; left: 21%; color: green; width: 10%;">After password change please login again</p>
<a href="/products" style="position: absolute; top: 2%; right: 7%">Back to main page</a>
<jsp:include page="logout.html" />
</body>
</html>
