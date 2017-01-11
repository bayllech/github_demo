<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <a href="/user/add"><button class="btn btn-primary">添加用户</button></a>
        <table class="table">
            <tr>
                <th>用户名</th>
                <th>密码</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
