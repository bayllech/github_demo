$(function(){

    $("#regBtn").click(function () {
        $("#regForm").submit();
    });

    $("#regForm").validate({
        errorElement:'span',
        errorClass:'text-error',
        rules:{
            username:{
                required:true,
                minlength:3,
                remote:"/validate/user"
            },
            password:{
                required:true,
                rangelength:[6,18]
            },
            repassword:{
                required:true,
                rangelength:[6,18],
                equalTo:"#password"
            },
            email:{
                required:true,
                email:true,
                remote:"/validate/email"
            },
            phone:{
                required:true,
                rangelength:[11,11],
                digits:true
            }
        },
        messages:{
            username:{
                required:"请输入账号",
                minlength:"账号最少3个字符",
                remote:"账号已被占用"
            },
            password:{
                required:"请输入密码",
                rangelength:"密码长度6-18个字符"
            },
            repassword:{
                required:"请确认密码",
                rangelength:"密码长度6-18个字符",
                equalTo:"两次密码不一致"
            },
            email:{
                required:"请输入电子邮件",
                email:"邮件格式错误",
                remote:"电子邮件已被占用"
            },
            phone:{
                required:"请输入手机号码",
                rangelength:"手机号码格式错误",
                digits:"手机号码格式错误"
            }
        },
        submitHandler:function () {
            $.ajax({
                url:"/reg",
                type:"post",
                data:$("#regForm").serialize(),
                beforeSend:function(){
                    $("#regBtn").text("注册中...").attr("disabled","disabled");
                },
                success:function(data){
                    if(data.state == 'success') {
                        // alert("注册成功，请登录并及时去邮箱验证！");
                        // window.location.href = "/login";
                        swal({
                                title: "注册成功!",
                                text: "请及时去邮箱验证,邮箱是您找回账号的唯一凭证,同时未验证账号无法登录.",
                                type: "success",
                                showCancelButton: true,
                                confirmButtonColor: "#4352dd",
                                confirmButtonText: "确定",
                                // cancelButtonText: "取消",
                                closeOnConfirm: false,
                                closeOnCancel: false
                            },
                            function(isConfirm){
                                if (isConfirm) {
                                    window.location.href = "/login";
                                } else {
                                    window.location.href="/home";
                                }
                            });
                    } else {
                        // alert(data.message);
                        swal({
                            title: data.message,
                            text: "",
                            type:"error",
                            timer: 1200,
                            showConfirmButton: true
                        });
                    }
                },
                error:function(){
                    alert("服务器错误");
                },
                complete:function(){
                    $("#regBtn").text("注册").removeAttr("disabled");
                }
            });
        }
    });


});
