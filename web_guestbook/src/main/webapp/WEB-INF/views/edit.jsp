<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/11/23
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改留言</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <nav class="navbar navbar-default" role="navigation">
            <div class="text-center"><h3>每个账号只能看到自己的留言</h3></div>
        </nav>
        <div class="col-md-8">
            <form action="/edit" method="post">
                <div>
                    <input type="hidden" name="id" value="${book.id}">
                </div>
                <div class="form-group">
                    <lable>留言内容:</lable>
                    <textarea name="message" rows="10" value="${book.message}" class="form-control">${book.message}</textarea>
                </div>
                <div class="form-group">
                    <lable>署名:</lable>
                    <input type="text" name="name" value="${book.name}" class="form-control">
                </div>
                <div><button class="btn btn-success">提交修改</button></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
