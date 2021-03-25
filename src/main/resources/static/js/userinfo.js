window.onload=function () {
    var listenopen=document.getElementById("add");
    var closeopen=document.getElementById("close");
    var open=document.getElementById("user-content");

    listenopen.addEventListener("click",function () {
        open.style.display = "block";
    });
    closeopen.addEventListener("click",function () {
        open.style.display = "none";

    })
}

