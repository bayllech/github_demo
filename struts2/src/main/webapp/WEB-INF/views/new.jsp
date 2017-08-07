<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>new</title>
</head>
<body>

<form action="/user/save">
    <input type="text" name="user.username">用户名
    <input type="password" name="user.password">密码
    <button>保存</button>

</form>
    <ul>
        <c:forEach items="${names}" var="name">
            <li>${name}</li>
        </c:forEach>
    </ul>

</body>
</html>
