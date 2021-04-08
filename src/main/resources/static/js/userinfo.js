
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

// $(document).ready(function() {
//
//
//
//     $("#LAY-component-form-setval").click(function() {
//
//         $.ajax({
//             url:"/add/userinfo",
//             type:"POST",
//             datatype:"JSON",
//             data: $('#formadd').serialize(),
//             success:function (data) {
//                 if(data.code==="0"){
//
//                     layer.open({
//                         content: '新增用户成功！',
//                         yes: function(index, layero){
//                             layer.close(index);
//
//                             document.getElementById("user-content").style.display="none";
//                             document.getElementById("username").value="";
//                             document.getElementById("password").value="";
//                             document.getElementById("email").value="";
//                             document.getElementById("age").value="";
//                             document.getElementById("position").value="";
//                             document.getElementById("line").value="";
//
//                         }
//                     });
//                     return true;
//                 }else {
//
//                     layer.open({
//                         content: data.errormsg,
//                         yes: function(index, layero){
//                             layer.close(index);
//                         }
//                     });
//                     return false;
//
//                 }
//
//             }
//         })
//
//     });
//
//
// });
