window.onload=function () {
    const listenopen = document.getElementById("modpassword");
    const closeopen = document.getElementById("close");
    const open = document.getElementById("moduser-1");

    listenopen.addEventListener("click",function () {
        open.style.display = "block";
    });
    closeopen.addEventListener("click",function () {
        open.style.display = "none";

    })
}


// $(document).ready(function() {
//
//     // 修改密码
//     $("#button").click(function() {
//         const FirstPassword = $("#FirstPassword").val();
//         const SecondPassword = $("#SecondPassword").val();
//
//         if (IsEmpty(FirstPassword)===false || IsEmpty(SecondPassword)===false){
//             $("#message").text("");
//             $("input[ type='text']").val("");
//             $("#error").text("密码不能为空");
//             return false;
//         }
//         if (IsNotNull(FirstPassword)===false || IsNotNull(SecondPassword)===false){
//             $("#message").text("");
//             $("input[ type='text']").val("");
//             $("#error").text("密码不能为特殊字符");
//             return false;
//         }
//
//         $.ajax({
//             url:"/Home/ModUser",
//             type:"POST",
//             datatype:"JSON",
//             data: $('#signinForm').serialize(),
//             success:function (data) {
//                 if(data.code==="0"){
//                     $("#error").text("");
//                     $("input[ type='text']").val("");
//                     $("#message").text("修改密码成功！");
//                     return true;
//                 }else if(data.errormsg==="SESSION已过期，请重新登陆！"){
//                     $(location).prop("href","/login")
//                     return true;
//
//                 } else {
//                     $("#message").text("");
//                     $("input[ type='text']").val("");
//                     $("#error").text(data.errormsg);
//                     return false;
//
//                 }
//
//             }
//         })
//
//     });
// });
