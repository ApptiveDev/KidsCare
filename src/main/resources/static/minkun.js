function ajaxIdDuplicated(loginId) {
console.log(loginId)
const postData = {
 loginId: loginId,
};

const option = {
    method: "POST",
    headers: {
    "Content-Type": "application/json",
    },
     body: JSON.stringify(postData),
};
    var res;
    fetch("/member/isIdDuplicated", option)
    .then(response => response.text())
    .then(text=>res= text);

console.log(res)
    if (res == true){//중복됨
    console.log("중복")
        return false;
    }
    else{ //중복안됨.
        console.log("중복안됨")
        return true;
    }
}
window.onload = function(){
    var loginId = document.getElementById("id");
    var idDuplicateCheckBtn = document.getElementById("idDuplicateCheckBtn");
    console.log("loginID : " + loginId);
    console.log(idDuplicateCheckBtn.value);
    idDuplicateCheckBtn.addEventListener("click", ajaxIdDuplicated(loginId.innerText));

}