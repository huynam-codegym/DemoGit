<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/30/2021
  Time: 1:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Danh sach khach hang</h1>
<table border="1px">
    <tr>
        <td>name</td>
        <td>email</td>
        <td>address</td>
        <td>Chi tiet </td>
    </tr>
    <c:forEach items="${customers}" var="c">

        <tr>
            <td> ${c.name}</td>
            <td> ${c.email}</td>
            <td> ${c.address}</td>
            <td> <a href="/customers?page=detail&id=${c.id}"> Chi tiet</a></td>



        </tr>


    </c:forEach>

</table>
</body>
</html>
