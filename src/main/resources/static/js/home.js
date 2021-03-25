window.onload=function () {
    var listenopen=document.getElementById("modpassword");
    var closeopen=document.getElementById("close");
    var open=document.getElementById("moduser-1");

    listenopen.addEventListener("click",function () {
        open.style.display = "block";
    });
    closeopen.addEventListener("click",function () {
        open.style.display = "none";

    })
}

$(document).ready(function() {

    // 修改密码
    $("#button").click(function() {
        var firstpassword=$("#firstpassword").val();
        var secendpassword=$("#secendpassword").val();

        if (firstpassword.length===0 || secendpassword.length===0){
            $("#message").text("");
            $("input[ type='text']").val("");
            $("#error").text("密码不能为空");
            return false;
        }
        if (firstpassword==="NULL" || secendpassword==="NULL"){
            $("#message").text("");
            $("input[ type='text']").val("");
            $("#error").text("密码不能为特殊字符");
            return false;
        }
        if (firstpassword==="null" || secendpassword==="null"){
            $("#message").text("");
            $("input[ type='text']").val("");
            $("#error").text("密码不能为特殊字符");
            return false;
        }

        $.ajax({
            url:"/home/edituser",
            type:"POST",
            datatype:"JSON",
            data: $('#signinForm').serialize(),
            success:function (data) {
                if(data.code==="0"){
                    $("#error").text("");
                    $("input[ type='text']").val("");
                    $("#message").text("修改密码成功！");
                    return true;
                }else if(data.errormsg==="SESSION已过期，请重新登陆！"){
                    $(location).prop("href","/login")
                    return true;

                } else {
                    $("#message").text("");
                    $("input[ type='text']").val("");
                    $("#error").text(data.errormsg);
                    return false;

                }

            }
        })

    });
});
