$(document).ready(function() {

    // 用户登录
    $("#ButtonLogin").click(function() {
        const username = $("#username").val();
        const password = $("#password").val();

        if (username.length===0 || password.length===0){
            $("input[ type='text']").val("");
            $("#error").text("账户信息不能为空");
            return false;
        }
        if (username==="NULL" || password==="NULL"){
            $("input[ type='text']").val("");
            $("#error").text("账户信息不能为特殊字符");
            return false;
        }
        if (username==="null" || password==="null"){
            $("input[ type='text']").val("");
            $("#error").text("密码不能为特殊字符");
            return false;
        }

        $.ajax({
            url:"/login/account",
            type:"POST",
            datatype:"JSON",
            data: $('#FormLogin').serialize(),
            success:function (data) {
                if(data.code==="0"){
                    $("#error").text("");
                    $(location).prop("href","/home")
                    return true;
                }else {
                    $("input[ type='text']").val("");
                    $("input[ type='password']").val("");
                    $("#error").text(data.errormsg);
                    return false;

                }

            }
        })

    });

});
