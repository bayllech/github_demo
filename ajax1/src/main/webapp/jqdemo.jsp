<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/12/6
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <input type="text" id="username">
    <span id="loding" style="display: none"><img src="static/img/loding.gif" alt=""></span>
    <span id="text"></span>

    <script src="static/js/jquery-1.11.3.min.js"></script>
    <script>
        $(function () {
            $("#username").blur(function () {
                var name = $(this).val();
                $.get("/ajax",{"name":"ajax"},function (data) {
                    alert(data);
                });
               /* $.post("/ajax",{"name":name,"age":23})  //post()不可用？？？
                    .done(function(data){
                        $("#text").text(data);
                    })
                    .error(function(){
                        alert("服务器端错误");
                    });*/
               $.ajax({
                   url : "/ajax",
                   type : post,
                   data : {"name":name,"age":23},
                   timeout : 1000,
                   beforeSend : function () {
                       $("#loding").show();
                       $("#text").text("")
                   },
                   success : function (data) {
                       $("#text").text(data)
                   },
                   error : function () {
                       alert("服务器异常")
                   },
                   complete : function () {
                       $("#loding").hide()
                   }
               });

            });
        });
    </script>
</body>
</html>
