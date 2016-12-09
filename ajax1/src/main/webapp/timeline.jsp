<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div id="result"></div>
    <script src="static/js/jquery-1.11.3.min.js"></script>
    <script>
        $(function () {
            function call() {
                var maxId = 0;
                $.get("/timeline",{"maxId":maxId}).done(function(data){
                   if (data.length) {
                       for (var i = 0;i<data.length;i++) {
                            var item = data[i];
                            var html = "<h3>"+item.message+"</h3>";
                            if (maxId == 0) {
                                $(html).appendTo($("#result"));
                            } else {
                                $(html).prependTo($("#result"));
                            }
                       }
                       maxId = data[0].id;
                   }
                }).error(function () {
                    alert("服务器异常");
                    clearInterval(st);
                });
            };
            call();

            var st = setInterval(call, 5000);

        });
    </script>
</body>
</html>
