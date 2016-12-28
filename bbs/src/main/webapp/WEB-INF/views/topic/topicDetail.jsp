<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>备有论坛—${topic.title}</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <style>
        body{
            background-image: url(/static/img/bg.jpg);
        }
        .simditor .simditor-body {
            min-height: 100px;
        }
    </style>
</head>
<body>
<%@include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
            <li><a href="/home">首页</a> <span class="divider">/</span></li>
            <li class="active">${topic.node.nodename}</li>
        </ul>
        <div class="topic-head">
            <img class="img-rounded avatar" src="${topic.user.avatar}?imageView2/1/w/60/h/60" alt="">
            <h3 class="title">${topic.title}</h3>
            <p class="topic-msg muted"><a href="">${topic.user.username}</a> · <span id="topicTime">${topic.createTime}</span></p>
        </div>
        <div class="topic-body">
            ${topic.content} </div>
        <div class="topic-toolbar">
            <c:if test="${not empty sessionScope.curr_user}">
                <ul class="unstyled inline pull-left">
                    <c:choose>
                        <c:when test="${empty fav}">
                            <li><a href="javascript:;" id="favTopic">加入收藏</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="javascript:;" id="favTopic">取消收藏</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li><a href="">感谢</a></li>
                    <c:if test="${sessionScope.curr_user.id == topic.userid and topic.edit}">
                        <li><a href="/topicEdit?topicId=${topic.id}">编辑</a></li>
                    </c:if>
                </ul>
            </c:if>
            <ul class="unstyled inline pull-right muted">
                <li>点击${requestScope.topic.clicknum}</li>
                <li>收藏<span id="favTopicnum">${topic.favnum}</span></li>
                <li>感谢${requestScope.topic.thankyounum}</li>
            </ul>
        </div>
    </div>
    <!--box end-->

    <div class="box" style="margin-top:20px;">
        <div class="talk-item muted" style="font-size: 12px">
            <%--${topic.replynum}--%>
            ${fn:length(replyList)}个回复 | 直到<span id="lastReplyTime">${topic.lastReplyTime}</span>

        </div>

        <c:forEach items="${replyList}" var="reply" varStatus="vs">
            <div class="talk-item"><a href="" name="reply${vs.count}"></a>
                <table class="talk-table">
                    <tr>
                        <td width="50">
                            <img class="avatar" src="${reply.user.avatar}?imageView2/1/w/40/h/40" alt="">
                        </td>
                        <td width="auto">
                            <a href=""  style="font-size: 12px">${reply.user.username}</a> <span style="font-size: 12px" class="reply">${reply.createtime}</span>
                            <br>
                            <p style="font-size: 14px">${reply.content}</p>
                        </td>
                        <td width="70" align="right" style="font-size: 12px">
                            <a href="javascript:;" rel="${vs.count}" class="replyLink" title="回复"><i class="fa fa-reply"></i></a>&nbsp;
                            <span class="badge">#${vs.count}</span>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>

    </div>

    <c:choose>
        <c:when test="${not empty sessionScope.curr_user}">
            <a name="reply"></a>
            <div class="box" style="margin:20px 0px;">
                <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                <form id="replyForm" action="" style="padding: 15px;margin-bottom:0px;">
                    <input type="hidden" name="userid" value="${topic.userid}">
                    <input name="topicid" type="hidden" value="${topic.id}">
                    <textarea name="contents" id="editor"></textarea>
                </form>
                <div class="talk-item muted" style="text-align: right;font-size: 12px">
                    <span class="pull-left">请尽量让自己的回复能够对别人有帮助</span>
                    <button id="replyBtn" class="btn btn-primary">发布</button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="box" style="margin:20px 0px;">
                <div class="talk-item"> <h4>请<a href="/login?redirect=topicDetail?topicid=${topic.id}#reply"><b style="color: #2626ff">登录</b></a>后再回复</h4></div>
            </div>
        </c:otherwise>
    </c:choose>

</div>
<!--container end-->
<!--<editor-fold desc="script导入折叠">-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/editer/scripts/module.min.js"></script>
<script src="/static/js/editer/scripts/hotkeys.min.js"></script>
<script src="/static/js/editer/scripts/uploader.min.js"></script>
<script src="/static/js/editer/scripts/simditor.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/highlight.pack.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/zh-cn.js"></script>
<script src="/static/js/sweetalert.min.js"></script>
<!--</editor-fold>-->
<script>
    $(function(){
        <c:if test="${not empty sessionScope.curr_user}">
            var editor = new Simditor({
                textarea: $('#editor'),
                toolbar:false
                //optional options
            });

            $(".replyLink").click(function () {
                var count = $(this).attr("rel");
                var html = "<a href='#reply"+count+"'>#"+count+"</a>";
                editor.setValue(html + editor.getValue());
                window.location.href = "#reply";
            });
        </c:if>

        $("#replyBtn").click(function(){
            $("#replyForm").submit();
        });
        hljs.initHighlightingOnLoad();
        /*$("#replyForm").ready(function() {
            // Add Method too jQuery Validator
            jQuery.validator.addMethod("noSpace", function(value, element)
            { return value.indexOf(" ") < 0 && value != ""; }, "请不要输入空格");
        });*/
        $("#replyForm").validate({
               errorElement:'span',
               errorClass:'text-error',
               rules:{
                   contents:{
                       required:true,
//                       noSpace:true
                   }
               },
               ignore:'',
               messages:{
                   contents:{
                       required:"回复不能为空",
                   }
               },
               submitHandler:function (form) {
                   $.ajax({
                       url: "/newreply",
                       type: "post",
                       data: $(form).serialize(),
                       beforeSend: function () {
                           $("#replyBtn").text("发布中").attr("disabled", "disabled");
                       },
                       success: function (data) {
                            if (data.state == "success") {
//                                alert("回复成功！");
                                swal({
                                        title: "回复成功",
                                        text: "",
                                        type: "success",
                                        // showCancelButton: true,
                                        confirmButtonColor: "#4352dd",
                                        confirmButtonText: "确定"
                                        // cancelButtonText: "取消",
                                    },
                                    function(){
                                        window.location.href="/topicDetail?topicid="+${topic.id};
                                    });
                                <%--window.location.href="/topicDetail?topicid="+${topic.id};--%>
                            } else {
//                                alert(data.message);
                                /*swal({
                                 title: data.message,
                                 text: "",
                                 type:"error",
                                 //                                    timer: 1200,
                                 showConfirmButton: true
                                 });*/
                                swal(data.message,"","error");
                            }
                       },
                       error: function () {
//                           alert("服务器异常");
                          /* swal({
                               title: "服务器异常",
                               text: "",
                               type:"error",
//                               timer: 1200,
                               showConfirmButton: true
                           });*/
                           swal("服务器异常","", "error");
                       },
                       complete: function () {
                           $("#replyBtn").text("发布").removeAttr("disabled");
                       }
                   });
               }
           });

        $("#topicTime").text(moment($("#topicTime").text()).fromNow());
        $("#lastReplyTime").text(moment($("#lastReplyTime").text()).format("YYYY年MM月DD日 HH:mm:ss"));
        $(".reply").text(function () {
            var time = $(this).text();
            return moment(time).fromNow();
        });

        $("#favTopic").click(function () {
            var $this = $(this);
            var action = "";
            if ($this.text() == "加入收藏") {
                action = "fav";
            } else {
                action = "unfav";
            }
            $.post("/favTopic",{"topicid":${topic.id},"action":action}).done(
                function (data) {
                    if (data.state == "success") {
                        if (action == "fav") {
                            $this.text("取消收藏");
                        } else {
                            $this.text("加入收藏");
                        }
                        $("#favTopicnum").text(data.topic.favnum);
                    } else {
//                        alert(data.message);
                        /*swal({
                            title: data.message,
                            text: "",
                            type:"error",
//                            timer: 1200,
                            showConfirmButton: true
                        });*/
                        swal(data.message, "", "error");
                    }
                }).error(
                    function () {
//                        alert("服务器开小差了，请稍后再试");
                        /*swal({
                            title: "服务器异常",
                            text: "",
                            type:"error",
//                            timer: 1200,
                            showConfirmButton: true
                        });*/
                        swal("服务器异常", "", "error");
                });
        });

    });
</script>

</body>
</html>
