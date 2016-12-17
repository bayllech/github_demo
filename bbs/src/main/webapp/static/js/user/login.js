/**
 * Created by bayllech on 2016/12/16.
 */
$(function () {
    $("#loginbtn").click(function () {
        $("#formLogin").submit();
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
                    if (date.state == "success") {
                        alert("登录成功！");
                        window.location.href="/home";
                    } else {
                        alert(data.messages);
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
