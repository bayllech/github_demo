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
    <title>留言列表</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
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
            margin-top: 214px;
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
            <a style="color:#000000" href="/add" class="btn btn-primary">添加留言</a>
            <a style="color:#000000" href="/logout" class="btn btn-default">安全退出</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>留言内容</th>
                        <th>来自</th>
                        <th>#</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty bookPage.items}">
                         <tr>
                             <td colspan="3">暂无数据</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${bookPage.items}" var="book">
                    <tr>
                        <td>${book.message}</td>
                        <td>${book.name}</td>
                        <td>
                            <span><a href="/edit?id=${book.id}"><h5>修改</h5></a></span>
                            <span><a href="javascript:;" rel="${book.id}" class="del"><h5>删除</h5></a></span>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>

            </table>

            <nav>
                <ul id="pagination" class="pagination pull-right"></ul>
            </nav>
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
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>

        $(function () {
            //分页插件的使用
            $("#pagination").twbsPagination({
                totalPages:${bookPage.totalPages},
                visiblePages:5,
                href:"/list?p={{number}}",
                first:"首页",
                prev:"上一页",
                next:"下一页",
                last:"末页"
            });

            $(".del").click(function () {
                if(confirm("你确定要删除吗？")) {
                    var id = $(this).attr("rel");
                    location.href = "/del?id=" + id;
                }
            });
        });
    </script>

</body>
</html>
