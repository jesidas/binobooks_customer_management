layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    form.on('submit(addOrUpdateCustomer)', function (data) {

        // Reference:（https://layer.layui.com/）
        var index = top.layer.msg("Data Submitting, Please wait...",{
            icon:16,
            time:false,
            shade:0.8
        });

        var formData = data.field;
        console.log(formData);

        var url = ctx + "/customer/add";

        var id = $("[name='id']").val();
        if(id != null && id != '') {
            var url = ctx + "/customer/update";
        }
        console.log(url);
        $.post(url, formData, function (result) {
            if (result.code == 200) {
                top.layer.msg("Operation success！",{icon:6});
                top.layer.close(index);
                layer.closeAll("iframe");
                parent.location.reload();
            } else {
                layer.msg(result.msg, {icon:5});
            }
        });

        return false;
    });


    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });


});