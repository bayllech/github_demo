<%--
  Created by IntelliJ IDEA.
  User: 帅比
  Date: 2016/12/6
  Time: 21:11
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
    <button id="btn">load json</button>

    <script src="static/js/jquery-1.11.3.min.js"></script>
    <script>
        $("#btn").click(function () {
            $.getJSON("/data.json").done(function(data){
                for (var i=0;i<data.length;i++) {
                    var user = data[i];
                    alert(user.name + "->" + user.address);
                }
            }).error(function () {
                alert("服务器异常")
            });
            
        });


        /*(function () {
            document.querySelector("#btn").onclick = function () {
              var xmlHttp = new XMLHttpRequest();
              xmlHttp.open("get", "/data.json");
              xmlHttp.onreadystatechange = function () {
                  if (xmlHttp.readyState == 4) {
                      if(xmlHttp.status == 200) {
                          var result = xmlHttp.responseText;
                          var json = JSON.parse(result);

                          for (var i = 0; i < json.length; i++) {
                              var user = json[i];
                              alert(user.id + "->" + user.name + "->" + user.address);
                          }

                      }
                  }
              }
              xmlHttp.send();
            };
        })();*/
    </script>
</body>
</html>
