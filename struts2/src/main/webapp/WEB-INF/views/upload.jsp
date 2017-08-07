<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="/file/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="file" name="file">
        <a href="/file/download">下载文件</a>
        <button>Upload</button>
    </form>
</body>
</html>
