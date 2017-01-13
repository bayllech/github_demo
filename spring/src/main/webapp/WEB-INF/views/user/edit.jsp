<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <form method="post">
            <div class="form-group">
                <input type="hidden" value="${user.id}" name="id">
                <label>用户名:</label>
                <input type="text" name="username" class="form-control" value="${user.username}">
            </div>
            <div class="form-group">
                <label>密码(如果不修改,请留空):</label>
                <input type="password" name="password" value="" class="form-control">
            </div>
            <div class="form-group">
                <button class="btn btn-success">提交</button>
            </div>
        </form>
    </div>
</body>
</html>
