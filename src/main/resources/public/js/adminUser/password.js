layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    form.on('submit(saveBtn)', function (data) {

        console.log(data.field);

        $.ajax({
            type:"post",
            url:ctx + "/adminUser/updateUserPwd",
            data:{
                userPwd:data.field.old_password,
                newPwd:data.field.new_password,
                repeatPwd:data.field.again_password
            },
            success:function (result) {

                if (result.code == 200) {

                    layer.msg("Successfully updated, return to login page in 3 secs...", function () {

                        $.removeCookie("userIdStr",{domain:"localhost",path:"/bnb"});
                        $.removeCookie("name",{domain:"localhost",path:"/bnb"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/bnb"});


                        window.parent.location.href = ctx + "/main";
                    });

                } else {
                    layer.msg(result.msg, {icon:5});
                }
            }

        });
    });


});