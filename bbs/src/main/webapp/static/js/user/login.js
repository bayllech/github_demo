/**
 * Created by bayllech on 2016/12/16.
 */
$(function () {
    //黑科技：获取parameter中的参数值
    function getParameterByName(name, url) {
        if (!url) {
            url = window.location.href;
        }
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    $("#loginbtn").click(function () {
        $("#formLogin").submit();
    });

    $("body").keydown(function () {
        if(event.keyCode == '13'){
            $("#formLogin").submit();
        }
    });

    $("#formLogin").validate({
        errorElement:'span',
        errorClass:'text-error',
        rules:{
            username:{
                required:true
            },
            password:{
                required:true
            }
        },
        messages:{
            username:{
                required:"请输入账号"
            },
            password:{
                required:"请输入密码"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url: "/login",
                type: "post",
                data: $(form).serialize(),
                beforeSend: function () {
                    $("#loginbtn").text("登录中...").attr("disabled", "disabled");
                },
                success: function (data) {
                    if (data.state == "success") {
                        // alert("登录成功！");
                        // swal("登录成功");
                        var url = getParameterByName("redirect");
                        swal({
                                title: "登录成功",
                                text: "",
                                type: "success",
                                // showCancelButton: true,
                                confirmButtonColor: "#4352dd",
                                confirmButtonText: "确定"
                                // cancelButtonText: "取消",
                            },
                            function(isConfirm){
                                if (isConfirm) {
                                    if (url) {
                                        var hash = location.hash;
                                        if (hash != null) {
                                            window.location.href= url + hash;
                                        }
                                    } else {
                                        window.location.href="/home";
                                    }
                                }
                            });
                    } else {
                        // alert(data.message);
                        // swal(data.message,"请注意区分大小写","error");
                        swal({
                            title: data.message,
                            text: "请注意区分大小写",
                            type:"error",
                            timer: 1200,
                            showConfirmButton: true
                        });
                    }
                },
                error: function () {
                    alert("服务器错误");
                },
                complete: function () {
                    $("#loginbtn").text("登录").removeAttr("disabled");
                }
            });
        }


    });
});
