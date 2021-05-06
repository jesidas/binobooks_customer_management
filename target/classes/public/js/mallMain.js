layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // Initialization the Menu
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();


    /**
     * Log Out
     */
    $(".login-out").click(function () {

        // Query User Pop Out
        layer.confirm('Confirm to Exit System?', {icon: 3, title:'System Reminder'}, function(index){
            // Close Pop Up Window
            layer.close(index);

            // Clear cookies
            $.removeCookie("userIdStr",{domain:"localhost",path:"/bnb"});
            $.removeCookie("name",{domain:"localhost",path:"/bnb"});
            $.removeCookie("trueName",{domain:"localhost",path:"/bnb"});

            // Redirect To Login Page（Parent Page Redirect）
            // window.parent.location.href = ctx + "/login";
            // // Redirect To Login Page（Parent Page Redirect）
            window.parent.location.href = ctx + "/mallUser/logout";
            window.parent.location.href = ctx + "/mallUserLogin"
        });
    });


});