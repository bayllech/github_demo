<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>劳务派遣</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="work_out_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">劳务派遣合同列表</h3>

                    <div class="box-tools pull-right">
                        <a href="/workOut/rent/add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>流水号</th>
                            <th>租赁公司</th>
                            <th>电话</th>
                            <th>租赁日期</th>
                            <th>规定归还日期</th>
                            <th>状态</th>
                            <th>租金</th>
                            <th>违约金</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach items="${rentList}" var="rent">
                                <tr>
                                <td>${rent.serialNum}</td>
                                <td>${rent.companyName}</td>
                                <td>${rent.tel}</td>
                                <td>${rent.rentDate}</td>
                                <td>${rent.backDate}</td>
                                <td>未完成</td>
                                <td>${rent.totalPrice}</td>
                                <td>暂无</td>
                                <td><a href="javascript:;">续费</a>
                                    <a href="javascript:;">入库</a>
                                    <a href="javascript:;">下载合同</a></td>
                                </tr>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
</body>
</html>
