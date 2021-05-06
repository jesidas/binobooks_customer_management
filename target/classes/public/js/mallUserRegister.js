layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    /**
     * 表单submit提交
     *  form.on('submit(按钮的lay-filter属性值)', function(data){
     *
     *       return false; //阻止表单跳转。
     *  });
     */
    form.on('submit(register)', function(data){

        console.log(data.field)
        $.ajax({
            type:"post",
            url: ctx + "/mallUser/register",
            data:{
                name:data.field.name,
                email:data.field.email,
                phone:data.field.phone
            },
            success:function (result) {
                console.log(result);
                if (result.code == 200) {
                    /**
                     *Set the user to login
                     *1. Using session
                     *Save the user information. If the session exists, the user is in the login state; otherwise, the user is not in the login state
                     *Disadvantages: when the server is shut down, the session will fail
                     *2. Using cookies
                     *If the user information is saved and the cookie is not invalid, the user is logged in
                     */
                    layer.msg("Register Success！", function () {
                        // if ($("#rememberMe").prop("checked")) {
                        //     $.cookie("userIdStr",result.result.userIdStr, {expires:7});
                        //     // $.cookie("name",result.result.name, {expires:7});
                        //     // $.cookie("trueName",result.result.trueName, {expires:7});
                        // } else {
                        //     $.cookie("userIdStr",result.result.userIdStr);
                        //     // $.cookie("name",result.result.name);
                        //     // $.cookie("trueName",result.result.trueName);
                        // }
                        window.location.href = ctx + "/register";
                    });
                } else {
                    layer.msg(result.msg, {icon:5});
                }
            }
        });

        return false;
    });


});