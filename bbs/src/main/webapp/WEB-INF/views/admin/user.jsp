<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.css" rel="stylesheet">
</head>
<script>
</script>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>账号</th>
            <th>注册时间</th>
            <th>最后登录时间</th>
            <th>最后登录IP</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="userVo">
            <tr>
                <td>${userVo.username}</td>
                <td class="creatime">${userVo.createtime}</td>
                <td class="lastetime">${userVo.lastLoginTime}</td>
                <td>${userVo.loginIP}</td>
                <td>
                    <a href="javascript:;" class="update" onClick="update(${userVo.userId},${userVo.userState})">${userVo.userState == '1'?'禁用':'恢复'}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination pagination-centered">
        <ul id="pagination" style="margin-bottom:20px;"></ul>
    </div>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script src="/static/js/sweetalert.min.js"></script>
<script src="/static/js/moment.js"></script>
<script>
    $(".creatime").text(function () {
        var time = $(this).text();
        return moment(time).format("YYYY年MM月DD日 HH:mm:ss");
    });
    $(".lastetime").text(function () {
        var time = $(this).text();
        return moment(time).format("YYYY年MM月DD日 HH:mm:ss");
    });
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

    function update(userid,userState){
        $.post("/admin/user",{"userid":userid,"userState":userState},function(json){
            if(json.state=='success') {
                window.history.go(0);
            }
        });
    }
</script>

</body>
</html>

