<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/12/12
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="static/css/simditor.css">
</head>
<body>

    <textarea id="editor" placeholder="请输入..." autofocus></textarea>

    <script src="static/js/jquery-1.11.3.min.js"></script>
    <script src="static/js/scripts//module.js"></script>
    <script src="static/js/scripts/hotkeys.js"></script>
    <script src="static/js/scripts/uploader.js"></script>
    <script src="static/js/scripts/simditor.js"></script>
    <script>
        var editor = new Simditor({
            textarea:$("#editor"),
            upload:{
                url:"http://up-z1.qiniu.com/",
                fileKey:"file",
                params:{"token":"${token}"}
            }
        });
    </script>

</body>
</html>
