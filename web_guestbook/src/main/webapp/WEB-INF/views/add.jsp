
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加留言</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-default  " role="navigation">
    <div class="text-center"><h3>每个账号只能看到自己的留言</h3></div>
</nav>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <c:if test="${not empty message}">
                    <div class="alert alert-danger">${message}</div>
                </c:if>
                <form action="/add" method="post">
                    <div class="form-group">
                        <lable>留言内容</lable>
                        <textarea name="message" value="${book.message}" class="form-control"></textarea>
                    </div>
                    <div class="form-group">
                        <lable>署名</lable>
                        <input type="text" name="name" value="${book.name}" class="form-control">
                    </div>
                    <div><button class="btn btn-success">保存</button></div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
