<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/12/8
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
    <input type="text" placeholder="rss url" id="url">
    <button id="btn">load rss</button>
    <ul id="newsList"></ul>

    <script src="static/js/jquery-1.11.3.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                $("#newsList").html("");
                var rssUrl = $("#url").val();
                $.ajax({
                    url:"/rss.xml",
                    type:"get",
                    data:{"rssUrl":rssUrl},
                    success:function (data) {
                        $(data).find("item").each(function () {
                            var title = $(this).find("title").text();
                            var link = $(this).find("link").text();
                            $("<li><a href='"+link+"' target='_blank'> "+title+"</a></li>").appendTo($("#newsList"));
                        });

                    },
                    error:function () {
                        alert("服务器异常")
                    }
                });
            });
        });
    </script>
</body>
</html>
