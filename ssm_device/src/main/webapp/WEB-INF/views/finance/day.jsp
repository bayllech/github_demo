<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务列表</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/dataTable/css/jquery.dataTables.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="finance"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">账单管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/finance/add" class="btn" title="添加账单"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                                ${message}
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>
                    <table class="table" id="table">
                        <thead>
                        <tr>
                            <th>财务流水号</th>
                            <th>财务流水名称</th>
                            <th>业务类型</th>
                            <th>金额</th>
                            <th>状态</th>
                            <th>创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${financeList}" var="finance">
                            <tr>
                                <td>${finance.serialNum}</td>
                                <td>${finance.remark}</td>
                                <td>${finance.type}</td>
                                <td>${finance.money}</td>
                                <td>${finance.state}</td>
                                <td>${finance.createDate}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/dataTable/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function(){
        $("#table").DataTable();
    });
</script>
</body>
</html>
