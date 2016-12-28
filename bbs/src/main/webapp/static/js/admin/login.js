/**
 * Created by bayllech on 2016/12/28.
 */
$(function(){

    $("#loginBtn").click(function(){
        $("#loginForm").submit();
    });

    $("#password").keydown(function () {
        if(event.keyCode == '13'){
            $("#loginBtn").click();
        }
    })
    $("#loginForm").validate({
        errorElement:"span",
        errorClass:"text-error",
        rules:{
            adminName:{
                required:true
            },
            password:{
                required:true
            }
        },
        messages:{
            adminName:{
                required:"请输入账号"
            },
            password:{
                required:"请输入密码"
            }
        },
        submitHandler:function(form){
            $.ajax({
                url:"/admin/login",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function(){
                    $("#loginBtn").text("登录中...").attr("disabled","disabled");
                },
                success:function(data){
                    if(data.state == 'success') {
                        window.location.href = "/admin/home";
                    } else {
                        swal(data.message, "", "error");
                    }
                },
                error:function(){
                    swal("服务器异常", "", "error");
                },
                complete:function(){
                    $("#loginBtn").text("登录").removeAttr("disabled");
                }
            });
        }
    });



});