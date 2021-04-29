

function Authority(){
    $.ajax({
        url:"/Functional/Authority",
        type:"POST",
        datatype:"JSON",
        success:function (data) {
            if(data.code==="0"){
                if(data.data.Userinfo===true){

                    $("#add").removeAttr("style").css("background-color","#009688");
                    $("#mod").removeAttr("style").css("background-color","#0082c8");
                    $("#del").removeAttr("style").css("background-color","#EA5514");
                }

                return true;
            } else {
                $("#message").text("");
                $("input[ type='text']").val("");
                $("#error").text(data.errormsg);
                return false;

            }

        }
    })
}