window.onload=function () {

    const listenopen = document.getElementById("addproject");
    const closeopen = document.getElementById("close");
    const open = document.getElementById("user-content");

    listenopen.addEventListener("click",function () {
        open.style.display = "block";
    });
    closeopen.addEventListener("click",function () {
        open.style.display = "none";

    })


    querydata();
}

$(document).on('click','#LAY-component-form-setval',function (){

    if(IsEmpty($("#project").val())===false || IsEmpty($("#content").val())===false){
        layer.confirm("项目名称和内容不能为空", {
            btn: ['确认']
        });
        return false

    }


    $.ajax({
        url:"/add/projectinfo",
        type:"POST",
        datatype:"JSON",
        data:$('#formadd').serialize(),
        success:function (data) {
            if (data.code === "0"){
                var projectlist = data.data;

                if(projectlist.length!==0){
                    let n = projectlist.length;
                    for(let i=0;i<n;i++){
                        AddProject(projectlist[i]);
                    }

                }
                $("#user-content").attr("style","display:none");

                return true

            }
            layer.confirm(data.errormsg, {
                btn: ['确认']
            });
            return false;

        }
    })

})

function AddProject(data){

    $("#add_card").append('<div class="layui-col-md6"><div class="layui-card">' +
        '<div class="layui-card-header"><span>'+data.name+'</span><span class="number" style="float: right">'+data.serial+'</span></div>' +
        '<div class="layui-card-body">'+data.content+'</div>' +
        '<div class="layui-card-header"><span style="float: right">'+data.creator+'</span></div>' +
        '</div></div>');

    return true
}

function querydata(){

    $.ajax({
        url:"/query/projectdata",
        type:"POST",
        datatype:"JSON",
        success:function (data) {
            if (data.code === "0"){

                var projectlist = data.data;

                if(projectlist.length!==0){
                    let n = projectlist.length;
                    for(let i=0;i<n;i++){
                        AddProject(projectlist[i]);
                    }

                }

                return true

            }
            layer.confirm(data.errormsg, {
                btn: ['确认']
            });
            return false;

        }
    })
}