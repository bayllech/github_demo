<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<div>


</div>
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>日期</th>
            <th>新主题数</th>
            <th>新回复数</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="homeView">
            <tr>
                <td>
                    ${homeView.data}
                </td>
                <td>${homeView.topicnum}</td>
                <td>${homeView.replynum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination pagination-right">
        <ul id="pagination" style="margin-bottom:20px;"></ul>
    </div>

</div>
<!--container end-->
</body>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '?p={{number}}'
        });

    });
</script>
</html>
