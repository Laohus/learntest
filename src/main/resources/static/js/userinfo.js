
window.onload=function () {
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

$(document).ready(function() {

    $("#LAY-component-form-setval").click(function() {

        $.ajax({
            url:"/add/userinfo",
            type:"POST",
            datatype:"JSON",
            data: $('#formadd').serialize(),
            success:function (data) {
                if(data.code==="0"){
                    layer.confirm("新增用户成功！", {
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

    });

});
