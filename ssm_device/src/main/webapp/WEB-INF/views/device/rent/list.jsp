<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="device_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-search"></i> 搜索</h3>
                </div>
                <div class="box-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" id="q_serialNum" placeholder="流水号" value="${queryName}" class="form-control">
                        </div>
                        <button type="button" id="searchBtn" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">租赁合同列表</h3>

                    <div class="box-tools pull-right">
                        <a href="/device/rent/new" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>id</th>
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
                       <%-- <tbody>
                        <tr>
                            <c:forEach items="${rentList}" var="rent">
                                <tr>
                                    <td>${rent.id}</td>
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
                                </tr>
                            </c:forEach>
                        </tr>
                        </tbody>--%>
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

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/layer.js"></script>
<script>
    $(function () {
        var table = $(".table").DataTable({
//            fixedHeader: true,
            "lengthChange": false,
            /*"lengthMenu": [10,50,100],*/
            "pageLength": 25,
            "serverSide": true,
            "ordering": false,//不使用各列的排序
            "ajax":{
                "url":"/device/rent/list",
                "type":"get",
                "data":function(obj){
                    obj.serialNum= $("#q_serialNum").val();
                }
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式
            "columns":[
                {"data":"id","name":"id"},
                {"data":function (row) {
                    return "<a href='/device/rent/"+row.serialNum+"'>"+row.serialNum+"</a>"
                }},
                {"data":"companyName"},
                {"data":"tel"},
                {"data":"rentDate"},
                {"data":"backDate"},
                {"data":function () {
                    return "未完成"
                }},
                {"data":"totalPrice"},
                {"data":function () {
                    return "暂无"
                }},
                {"data":function(obj){
                    return "<a href='javascript:;' rel='"+obj.id+"' class='btn btn-xs btn-default check'><i class='fa fa-check'></i>入库</a>" +
                           "<a href='javascript:;' rel='"+obj.id+"' class='btn btn-xs btn-default continue'><i class='fa fa-check'></i>续费</a>";
                }}

            ],
            "columnDefs":[
                {targets:[0],visible: false},
//                {targets:[1,2,3,4,5,6,7,8,9],orderable:false}
            ],
            "language":{ //定义中文
                "search": "搜索:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing":     "处理中...",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }
        });
//        new $.fn.dataTable.FixedHeader(table,{});


        $(document).delegate(".check","click",function(){
            layer.confirm("确定要将合同入库吗?",function (index) {
                var id = $(this).attr("rel");
                $.get("/device/rent/state/change"+{"id":id}).done(function(data){
                    if(data == "success") {
                        layer.msg("入库成功");
                        //dataTables重新加载
                        table.ajax.reload();
                    }
                }).error(function(){
                   layer.msg("服务器异常");
                });
                layer.close(index);
            });
        });

        //自定义搜索
        $("#searchBtn").click(function () {
            table.draw(); //dataTables发出请求
        });


    });
</script>

</body>
</html>
