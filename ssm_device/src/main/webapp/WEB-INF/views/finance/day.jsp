<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务报表</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.min.css">
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
                    <h3 class="box-title"><i class="fa fa-search"></i> 搜索日报</h3>
                </div>
                <div class="box-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" id="date" class="form-control">
                        </div>
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">财务日报</h3>

                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>财务流水</th>
                            <th>财务名称</th>
                            <th>业务模块</th>
                            <th>业务流水</th>
                            <th>金额</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>类型</th>
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

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/layer.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
    $(function () {
        $("#date").val(moment().format("YYYY-MM-DD"));
        $("#date").datepicker({
            format:"yyyy-mm-dd",
            language:"zh-CN",
            autoclose:true,
            endDate:moment().format("YYYY-MM-DD")
        }).on("changeDate",function (e) {
            var today = e.format(0, "yyyy-mm-dd");
            table.ajax.reload();
        });

        var table = $(".table").DataTable({
//            fixedHeader: true,
            "lengthChange": false,
            /*"lengthMenu": [10,50,100],*/
            "pageLength": 25,
            "serverSide": true,
            "ordering": false,//不使用各列的排序
            "ajax":{
                "url":"/finance/day/load",
                "type":"get",
                "data":function(obj){
                    obj.date= $("#date").val();
                }
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式
            "columns":[
                {"data":"id","name":"id"},
                {"data":"financeSerial"},
                {"data":"remark"},
                {"data":"module"},
                {"data":"serialNum"},
                {"data":"money"},
                {"data":"state"},
                {"data":"createDate"},
                {"data":"type"},
                {"data":function(obj){
                    if(obj.state == "未完成"){
                        return "<a href='javascript:;' rel='" + obj.id + "' class='btn btn-xs btn-default confirm'><i class='fa fa-check'></i>完成</a>"
                    } else {
                        return "";
                    }
                }
                }

            ],
            "columnDefs":[
                {targets:[0],visible: false}
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


        $(document).delegate(".confirm","click",function(){
            var id = $(this).attr("rel");
            layer.confirm("确认已收(付)款?",function (index) {
                $.post("/finance/confirm",{"id":id}).done(function(data){
                    if(data == "success") {
                        layer.msg("确认成功");
                        //dataTables重新加载
                        table.ajax.reload(false,null);
                    } else {
                        layer.msg("此流水号已不存在");
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });
                layer.close(index);
            });
        });



    });
</script>

</body>
</html>
