<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主题管理</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/sweetalert.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <table class="table">
        <thead>
        <tr>
            <th>名称</th>
            <th>发布人</th>
            <th>发布时间</th>
            <th>回复数量</th>
            <th>最后回复时间</th>
            <th>所属节点</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="topic">
            <tr>
                <td>
                    <a href="/topicDetail?topicid=${topic.id}" target="_blank">${topic.title}</a>
                </td>
                <td>${topic.user.username}</td>
                <td>${topic.createTime}</td>
                <td>${topic.replynum}</td>
                <td>${topic.lastReplyTime}</td>
                <td>
                    <select name="nodeid" id="nodeid">
                        <option value="">请选择节点</option>
                        <c:forEach items="${nodeList}" var="node">
                            <option ${topic.nodeid == node.id ? 'selected' : ''} value="${node.id}">${node.nodename}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><a href="javascript:;" rel="${topic.id}" class="update">修改节点</a></td>
                <td><a href="javascript:;" rel="${topic.id}" class="del">删除</a></td>
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
<script>
    $(function(){
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '?p={{number}}&_=${param._}'
        });

        $(".update").click(function(){
            var topicid = $(this).attr("rel");
            var nodeid = $("#nodeid").val();
            $.ajax({
                url:"/admin/topicUpdate",
                type:"post",
                data:{"topicid":topicid,"nodeid":nodeid},
                success:function(data){
                    if(data.state == 'success') {
                        alert("修改成功!");
                        window.history.go(0);
                    } else {
                        swal(data.message,"","error");
                    }
                },
                error:function(){
                    swal("服务器异常,删除失败!","","error");
                }
            });
        });
        $(".del").click(function () {
            var topicid = $(this).attr("rel");
            swal({
                title: "确定要删除该主题及其所有回复吗?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                closeOnConfirm: false },
                function(){
                    $.ajax({
                        url:"/admin/topic",
                        type:"post",
                        data:{"topicid":topicid},
                        success:function(data){
                            if(data.state == 'success') {
                                swal({title:"删除成功!"},function () {
                                    window.history.go(0);
                                });
                            } else {
                                swal(data.message,"","error");
                            }
                        },
                        error:function(){
                            swal("服务器异常,删除失败!","","error");
                        }
                    });

                });
        })
    });
</script>
</body>
</html>

