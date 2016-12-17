/**
 * Created by bayllech on 2016/12/17.
 */
$(function () {
    $("#type").change(function () {
        var value = $(this).val();
        if (value == "email") {
            $("#typename").text("电子邮件");
        } else if (value == "phone") {
            $("#typename").text("手机号码");
        };
    });

   $("#btn").click(function () {
       $("#form").submit();
   });

   $("#form").validate({
          errorElement:'span',
          errorClass:'text-error',
          rules:{
              value:{
                  required:true
              }
          },
          messages:{
              value:{
                  required:"该项必填"
              }
          },
          submitHandler:function () {
              $.ajax({
                  url: "/forgetPassword",
                  type: "post",
                  data: $(form).serialize(),
                  beforeSend: function () {
                      $("#btn").text("提交中...").attr("disabled", "disabled");
                  },
                  success: function (data) {
                      if (data.state == "success") {
                          var type = $("#type").val();
                          if (type == "email") {
                              alert("请到邮件中查收并操作");
                          } else {
                              //TODO 手机号码验证
                          }
                      } else {
                          alert(data.message);
                      }
                  },
                  error: function () {
                      alert("服务器异常")
                  },
                  complete: function () {
                      $("#btn").text("提交").removeAttr("disabled");
                  }
              });
          }
      });

});