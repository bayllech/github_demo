/**
 * Created by bayllech on 2016/12/19.
 */
$(function () {

    $("#basicBtn").click(function () {
        $("#basicForm").submit();
    });

    $("#basicForm").validate({
           errorElement:'span',
           errorClass:'text-error',
           rules:{
               email:{
                   required:true,
                   email:true,
                   remote:"/validate/email?type=1"
               }
           },
           messages:{
               email:{
                   required:"请输入邮箱",
                   email:"邮箱格式错误",
                   remote:"邮箱已被占用"
               }
           },
           submitHandler:function (form) {
               $.ajax({
                   url: "/setting?action=profile",
                   type: "post",
                   data: $(form).serialize(),
                   beforeSend: function () {
                       $("#basicBtn").text("保存中...").attr("disabled", "disabled");
                   },
                   success: function (data) {
                       if (data.state == "success") {
                           alert("邮箱修改成功，请及时去邮箱验证");
                       } else {
                           alert(data.message);
                       }
                   },
                   error: function () {
                       alert("服务器异常");
                   },
                   complete: function () {
                       $("#basicBtn").text("保存").removeAttr("disabled");
                   }
               });
           }
       });

    $("#passwordBtn").click(function () {
        $("#passwordForm").submit();
    });

    $("#passwordForm").validate({
           errorElement:'span',
           errorClass:'text-error',
           rules:{
               oldpassword:{
                   required:true,
                   rangelength:[6,18]
               },
               newpassword:{
                   required:true,
                   rangelength:[6,18]
               },
               repassword:{
                   required:true,
                   rangelength:[6,18],
                   equalTo:"#newpassword"
               }
           },
           messages:{
               oldpassword:{
                   required:"请输入原始密码",
                   rangelength:"密码长度6-18位"
               },
               newpassword:{
                   required:"请输入新密码",
                   rangelength:"密码长度6-18位"
               },
               repassword:{
                   required:"请重复密码",
                   rangelength:"密码长度6-18位",
                   equalTo:"两次密码不一致"
               }
           },
           submitHandler:function (form) {
               $.ajax({
                   url: "/setting?active=password",
                   type: "post",
                   data: $(form).serialize(),
                   beforeSend: function () {
                       $("#passwordBtn").text("保存中...").attr("disabled", "disabled");
                   },
                   success: function (data) {
                       if (data.state == "success") {
                           alert("密码修改成功，请重新登录");
                           window.location.href = "/login";
                       } else {
                           alert(data.message);
                       }
                   },
                   error: function () {
                       alert("服务器异常")
                   },
                   complete: function () {
                       $("#passwordBtn").text("保存").removeAttr("disabled");
                   }
               });
           }
       });


});