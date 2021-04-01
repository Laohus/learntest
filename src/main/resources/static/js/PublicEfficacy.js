

window["IsEmpty"]=function (str){
    return !(str === null || str === "" || str.replace(/\s*/g,"") === "");
}

window["IsNotNull"]=function (str){
    return !(str .toLowerCase() === "null");
}