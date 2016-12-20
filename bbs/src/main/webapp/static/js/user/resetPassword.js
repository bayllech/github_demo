/**
 * Created by bayllech on 2016/12/17.
 */
$(function () {
   $("#resetBtn").click(function () {
       $("#resetForm").submit();
   });

   $("#resetForm").validate({
          errorElement:'span',
          errorClass:'text-error',
          rules:{
              password:{
                  required:true,
                  rangelength:[6,18]
              },
              repassword:{
                  required:true,
                  rangelength:[6,18],
                  equalTo:"#password"
              }
          },
          messages:{
              password:{
                  required:"请输入新密码",
                  rangelength:"密码6-18位"
              },
              repassword:{
                  required:"请重复新密码",
                  rangelength:"密码6-18位",
                  equalTo:"两次密码不一致"
              }
          },
          submitHandler:function (form) {
              $.ajax({
                  url: "/forgetPassword/newPassword",
                  type: "post",
                  data: $(form).serialize(),
                  beforeSend: function () {
                      $("#resetBtn").text("保存中...").attr("disabled", "disabled");
                  },
                  success: function (data) {
                      if (data.state == "success") {
                          // alert("密码设置成功，请登录！");
                          // window.location.href = "/login";
                          swal({
                                  title: "密码设置成功，请您重新登录",
                                  text: "",
                                  type: "success",
                                  // showCancelButton: true,
                                  confirmButtonColor: "#4352dd",
                                  confirmButtonText: "确定"
                                  // cancelButtonText: "取消",
                              },
                              function(isConfirm){
                                  if (isConfirm) {
                                      window.location.href = "/login";
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
                  error: function () {
                      alert("服务器异常");
                  },
                  complete: function () {
                      $("#resetBtn").text("保存").removeAttr("disabled");
                  }
              });
          }
      });

});