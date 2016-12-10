<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/12/10
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="static/js/webupload/webuploader.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body>
    <div id="picker">选择文件</div> <button id="startBtn">开始上传</button>
    <ul id="fileList"></ul>

    <script src="static/js/jquery-1.11.3.min.js"></script>
    <script src="static/js/webupload/webuploader.min.js"></script>
    <script type="text/template" id="bar">
        <div class="progress">
            <div class="progress-bar" id="{{id}}" style="width:0%;">
                <span class="sr-only"></span>
            </div>
        </div>
    </script>

    <script>
        $(function () {
            var uploader = WebUploader.create({
                swf:"static/js/webupload/Uploader.swf",
                server:"/upload",
                pick:"#picker",
                fileVal:"file",
                auto:true
            });
        });
    </script>
</body>
</html>
