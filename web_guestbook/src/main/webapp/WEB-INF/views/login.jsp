
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
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
            margin-top: 73px;
        }
        #change {
            text-decoration: none;
            display: block;
            width: 160px;
            height: 70px;
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
<div class="container">
    <div class="row">

        <nav class="navbar navbar-default" role="navigation">
            <div class="text-center"><h3>谢谢你的支持同时你也将成为网站内测第一批用户</h3></div>
        </nav>

        <c:if test="${not empty message}">
            <div class="alert alert-danger">${message}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>

        <div class="col-md-4">
            <form method="post" id="formSubmit">
                   <div class="form-group">
                       <lable>账号</lable>
                       <input type="text" name="name" value="${name}" class="form-control">
                   </div>
                    <div class="form-group">
                        <lable>密码</lable>
                        <input type="password" id="t_password" name="t_password" value="" class="form-control">
                        <input type="password" id="password" name="password" value="" hidden>
                    </div>
                <div class="form-group">
                    <a href="javascript:;" id="change">
                        <img id="img" src="/patchca.png" title="看不清？点击换一张">
                    </a>
                    <br>
                    <span>验证码:</span><input type="text" name="code">
                </div>


                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="remeberme" value="remeberme" >记住账号
                        </label>
                    </div>
                    <div><button type="button" id="buttonLogin" class="btn btn-success">登录</button> <a href="/signin">还没账号?点击注册</a></div>

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

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/CryptoJS/rollups/md5.js"></script>
<script>
    $(function () {
        $("#change").click(function () {
           $("#img").removeAttr("src").attr("src","/patchca.png?_="+new Date().getTime());
        });

        $("#buttonLogin").click(function () {
            var t_password = $("#t_password").val();
            password = CryptoJS.MD5(t_password);
            $("#password").val(password);

            $("#formSubmit").submit();
        })
    });
</script>

</body>
</html>
