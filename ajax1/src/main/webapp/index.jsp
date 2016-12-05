<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/12/5
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="" enctype="application/x-www-form-urlencoded"></form>
<input type="text" id="name">
<button id="btn">sendRequest</button>
<div id="result"></div>

<script>
    (function(){
        function createXmlHttp() {
            var xmlHttp = null;
            if (window.ActiveXObject) {   //注意细节，好不嘞
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        };

        document.querySelector("#btn").onclick = function () {
            var name = document.querySelector("#name").value;
            sendGet(name);
//            sendPost(name);
        };

        function sendGet(name) {
            var xmlHttp = createXmlHttp();
            xmlHttp.open("get","/ajax?name=" + name);
//            xmlHttp.open("get","/ajax?name=" + name + "&_time=" + new Date().getTime());
            xmlHttp.send();
        };

        function sendPost(name) {
            var xmlHttp = createXmlHttp();
            xmlHttp.open("post", "/ajax");
            xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            xmlHttp.onreadystatechange = function () {
                var state = xmlHttp.readyState;
                if (state==4) {
                    var httpState = xmlHttp.status;
                    if (httpState == 200) {
                        var result = xmlHttp.responseText;
                        var div = document.querySelector("#result");
                        if (result == "能用") {
                            div.innerText = "该账号可用";
                        } else {
                            div.innerText = "该账号已被使用";
                        }
                    } else {
                        alert("服务器错误" + httpState)
                    }
                }
            }
            xmlHttp.send("name="+name+"&age=23");

        }
    })();
</script>
</body>
</html>
