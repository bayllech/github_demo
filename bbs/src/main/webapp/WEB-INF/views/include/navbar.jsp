<%--
  Created by IntelliJ IDEA.
  User: bayllech
  Date: 2016/12/15
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header-bar">
    <div class="container">
        <a href="/home" class="brand">
            <i class="fa fa-home"></i>
        </a>
        <span hidden id="isLogin"><c:if test="${not empty sessionScope.curr_user}">1</c:if></span>
        <ul class="unstyled inline pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.curr_user}">
                    <li>
                        <a href="/setting">
                            <img id="navbar_avatar" src="http://ohyf2mhv9.bkt.clouddn.com/${sessionScope.curr_user.avatar}?imageView2/1/w/20/h/20" class="img-circle" alt="">
                        </a>
                    </li>
                    <li>
                        <a href="/newTopic"><i class="fa fa-plus"></i></a>
                    </li>
                    <li>
                        <a href="/notify"><i class="fa fa-bell"></i><span class="header-bar" id="unReadCount" style="/*z-index: 999;*/position: absolute;height: 15px;width: 15px;line-height: 15px;border-radius: 15px;top: 0.5%;left: 79%;background-color: #bf1031;padding: 2px 0px 2px 6px;font-size: 8px"></span></a>
                    </li>
                    <li>
                        <a href="/setting"><i class="fa fa-cog"></i></a>
                    </li>
                    <li>
                        <a href="/logout"><i class="fa fa-sign-out"></i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="/login"><i class="fa fa-sign-in"></i></a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        var login = $("#isLogin").text();
        if (login == "1") {
            setInterval(loadNotify,1*1000);
        }
        var loadNotify = function () {
            $.post("/notify",function(json){
                if (json.state == "success" && json.message > 0){
                    $("#unReadCount").text(json.message);
                }
            });
        };
        loadNotify();

    });
</script>

