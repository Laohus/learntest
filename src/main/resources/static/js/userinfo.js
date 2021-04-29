
window.onload=function () {

    Authority();

    const listenopen = document.getElementById("add");
    const closeopen = document.getElementById("close");
    const open = document.getElementById("user-content");

    listenopen.addEventListener("click",function () {
        open.style.display = "block";
    });
    closeopen.addEventListener("click",function () {
        open.style.display = "none";

    })
}

function DelInfo ( UserInfo ){

    $.ajax({
        url:"/del/userinfo",
        type:"POST",
        datatype:"JSON",
        contentType:"application/json",
        data:JSON.stringify(UserInfo),
        success:function (data) {
            if(data.code==="0"){
                layer.confirm("删除用户成功！", {
                    btn: ['确认']
                });
                return true;
            }else {
                layer.confirm(data.errormsg, {
                    btn: ['确认']
                });
                return false;
            }

        }
    });
    return false;

}


function AddInfo (){

    $.ajax({
        url:"/add/userinfo",
        type:"POST",
        datatype:"JSON",
        data: $('#formadd').serialize(),
        success:function (data) {
            if(data.code==="0"){

                layer.open({
                    content: '新增用户成功！',
                    yes: function(index, layero){
                        layer.close(index);
                    }
                });
                return true;
            }else {

                layer.open({
                    content: data.errormsg,
                    yes: function(index, layero){
                        layer.close(index);
                    }
                });
                return false;

            }

        }
    })
    return false;

}

function ModInfo (UserInfo){

    $.ajax({
        url:"/mod/userinfo",
        type:"POST",
        datatype:"JSON",
        contentType:"application/json",
        data:JSON.stringify(UserInfo),
        success:function (data) {
            if(data.code==="0"){
                layer.confirm("修改用户成功！", {
                    btn: ['确认']
                });

                return true;
            }else {
                layer.confirm(data.errormsg, {
                    btn: ['确认']
                });
                return false;

            }

        }
    })

    return false;

}

function clean(){

    $('#username').attr("readonly",false).css("background-color","#FFFFFF").val("");
    $('#age').attr("readonly",false).css("background-color","#FFFFFF").val("");
    $('#jobs').attr("readonly",false).css("background-color","#FFFFFF").val("");
    $('#password').val("");
    $('#email').val("");
    $('#line').val("");
    return true;
}

function HiddenControls(){

    $("#add").removeAttr("style").css("background-color","#009688");
    $("#mod").removeAttr("style").css("background-color","#0082c8");
    $("#del").removeAttr("style").css("background-color","#EA5514");
    return true;
}