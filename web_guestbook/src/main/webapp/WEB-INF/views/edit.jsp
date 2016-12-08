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
    <style>
        body{
            background-color: #e8ebef;
        }
        .fotter{
            padding: 40px 0px;
            background-color: #34495e;
            text-shadow: 0 1px 2px rgba(0,0,0,0.3);
            color: #878e98;
            line-height: 1.5;
        }

        .box{
            margin-top: 54px;
        }


        a:link {
            color: #878e98;
            text-decoration: none;
        }
        a:visited {
            text-decoration: none;
            color:#878e98;
        }
        a:hover {
            text-decoration: underline;
            color: #878e98;
        }
        a:active {
            text-decoration: none;
            color: #878e98;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="text-center"><h3>每个账号只能看到自己的留言</h3></div>
</nav>

<div class="container">
    <div class="row">
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

<div class="fotter box">
    <div class="container">
        <div class="row text-center">
            <div class="span9">
                <a href="http://bayllech.cn"><strong>备有网</strong></a> <br>
                <a href="maito:bayllech@gmail.com">邮箱:bayllech@gmail.com</a> <br>
                <a href="http://www.miit.gov.cn/" style="text-shadow: 0 1px 2px rgba(0,0,0,0.3);color: #878e98;font-size:12px">豫ICP备16037326号</a>

            </div>
        </div>
    </div>
</div>
</body>
</html>
