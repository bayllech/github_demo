<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册账号</title>
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
            margin-top: 165px;
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
                <div class="text-center"><h3>账号仅作为登录使用，不会记录个人信息</h3></div>
            </nav>

            <c:if test="${not empty message}">
                <div class="alert alert-danger">${message}</div>
            </c:if>

            <div class="col-md-4">

                <form id="formSubmit" method="post">
                    <div class="form-group">
                        <lable>账号：</lable>
                        <input type="text" name="username" value="" class="form-control">
                    </div>
                    <div class="form-group">
                        <lable>密码：</lable>
                        <input id="t_password" type="password" name="t_password" value="" class="form-control">
                        <input id="password" type="password" name="password" value="" hidden >
                    </div>
                    <div class="form-group">
                        <lable>确认密码：</lable>
                        <input id="t_repassword" type="password" name="t_repassword" value="" class="form-control">
                        <input id="repassword" type="password" name="repassword" value="" hidden>
                    </div>
                    <button type="button" id="buttonSignin" class="btn btn-success">注册</button>
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
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/messages_zh.min.js"></script>
    <script src="/static/js/CryptoJS/rollups/md5.js"></script>
    <script>
        $(function () {

            $("#buttonSignin").click(function () {
                var t_password = $("#t_password").val();
                password = CryptoJS.MD5(t_password);
                $("#password").val(password);

                var t_repassword  = $("#t_repassword").val();
                repassword = CryptoJS.MD5(t_repassword);
                $("#repassword").val(repassword);

                $("#formSubmit").submit();
            });

            $("#formSubmit").validate({
              //  errorLabelContainer: $("#formSubmit div.alert-danger"),
                errorElement:"span",
                errorClass:"text-danger",
                rules:{
                    username : {
                        required : true,
                        minlength: 3,
                        remote: "/signin"
                    },
                    t_password : {
                        required : true,
                        rangelength:[6,50]
                    },
                    t_repassword : {
                        required : true,
                        rangelength:[6,50],
                        equalTo:"#t_password"
                    }
                },

                messages:{
                    username : {
                        required : "请输入账号",
                        minlength: "账号最少3个字符",
                        remote:"账号已被使用"
                    },
                    t_password : {
                        required : "请输入密码",
                        rangelength:"密码长度不能小于6位"
                    },
                    t_repassword : {
                        required : "请输入确认密码",
                        rangelength:"密码长度不能小于6位",
                        equalTo:"两次密码不一致"
                    }
                }
            });
        });
    </script>

</body>
</html>
