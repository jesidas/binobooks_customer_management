/**
 *
 */
layui.use(['form', 'layer', 'formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var formSelects = layui.formSelects;


    /**
     * Form Submit Listening
     */
    form.on('submit(EditBasicInfo)', function (data) {

        // Loading layer When Submitting Data （https://layer.layui.com/）
        var index = top.layer.msg("Data Submitting, Please Wait...",{
            icon:16, // Icon
            time:false, // Do Not Close
            shade:0.8 // Setting Transparency Shade
        });

        // Obtain All Value of Form Elements
        var formData = data.field;
        console.log(formData);

        // Request Url
        var url = ctx + "/mallUser/add"; // Add Operation


        // Verify if userID is empty, update if not-null
        if ($("[name='id']").val()) {
            // 更新操作
            var url = ctx + "/mallUser/update";
        }


        $.post(url, formData, function (result) {
            // Verify if Operation is Success, Code 200= success
            if (result.code == 200) {
                // Success
                // Reminder Success
                top.layer.msg("Operation Success！",{icon:6});
                // Close Loading Layer
                top.layer.close(index);
                // Close Pop Up Layer
                layer.closeAll("iframe");
                // Update Parent Page, Reload Data
                parent.location.reload();
            } else {
                // Fail
                layer.msg(result.msg, {icon:5});
            }
        });

        // Prevent Form Submission
        return false;
    });


    /**
     * Close Pop Up Lyer
     */
    $("#closeBtn").click(function () {
        // When you close yourself in the iframe Layer
        var index = parent.layer.getFrameIndex(window.name); // Obtain the Current iframe Index
        parent.layer.close(index); // Close Operation then
    });



    /**
     * Load Role Pop Up Layer
     *
     * Configure remote search, Request Header, Request Params, Request Type , etc
     *
     * formSelects.config(ID, Options, isJson);
     * https://hnzzmsf.github.io/example/example_v4.html#methods-config
     * @param ID         Xm-Select Value
     * @param Options    Configure Category
     * @param isJson     Content-Type: application/json; charset=UTF-8
     */
    var userId = $("[name='id']").val();
    formSelects.config("selectId",{
        type:"post", // Request Approach
        searchUrl: ctx+"/role/queryAllRoles?userId="+userId, // Request Address
        keyName: 'roleName',  // The Text Content in the Pop Up Layer, Be Consistent with the Key Returned下拉框中的文本内容，要与返回的数据中对应key一致
        keyVal: 'id'
    }, true);
    
});