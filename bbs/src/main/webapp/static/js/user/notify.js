/**
 * Created by bayllech on 2016/12/27.
 */
$(function () {
    var login = $("#isLogin").text();
    if (login == "1") {
        setInterval(loadNotify,1*1000);
    }
    var loadNotify = function () {
        $.post("/notify",function(json){
            if (json.state == "success" && json.message > 0){
                alert(json.message);
                $("#unReadCount").text(json.message);
            }
        });
        /*$.post("/notify").done(
         function (json) {
         if (json.state == "success" && json.data > 0) {
         $("#unReadCount").text(json.data);
         }
         });*/
    };
    loadNotify();
});