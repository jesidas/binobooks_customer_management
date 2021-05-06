layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(login)', function(data){

        console.log(data.field)

        $.ajax({
            type:"post",
            url: ctx + "/adminUser/login",
            data:{
                name:data.field.name,
                userPwd:data.field.password
            },
            success:function (result) {
                console.log(result);
                if (result.code == 200) {
                     layer.msg("Login Success！", function () {
                        if ($("#rememberMe").prop("checked")) {
                            $.cookie("userIdStr",result.result.userIdStr, {expires:7});
                            // $.cookie("name",result.result.name, {expires:7});
                            // $.cookie("trueName",result.result.trueName, {expires:7});
                        } else {
                            //
                            $.cookie("userIdStr",result.result.userIdStr);
                            // $.cookie("name",result.result.name);
                            // $.cookie("trueName",result.result.trueName);
                        }
                        window.location.href = ctx + "/adminMain";
                    });

                } else {
                    layer.msg(result.msg, {icon:5});
                }
            }
        });
        return false;
    });


});