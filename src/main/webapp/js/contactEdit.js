window.onload = load;
var contactVariables = {
    telephoneLastId:0,
    attachmentLastId:0
};

function load(){
    loadTelephoneMangment();
    loadAttachmentsManagement();
    loadAvatarMangment()
}

function openModal(textLabel,id){
    document.body.setAttribute("class","modal-open");
    document.body.setAttribute("style","padding-right: 17px;");

    var el = document.createElement("span");
    el.setAttribute("name","deletableElement");
    el.setAttribute("class","modal-backdrop fade in");
    document.body.appendChild(el);

    setFullNameIn(textLabel,id+"Label");
    var modal = document.getElementById(id);
    modal.setAttribute("class","modal fade in");
    modal.setAttribute("style","display: block;");
}

function closeModal(id){
    document.body.removeAttribute("class");
    document.body.removeAttribute("style");
    var modal = document.getElementById(id);

    var elements =  document.getElementsByName("deletableElement");
    while(elements.length > 0){
        elements[0].parentNode.removeChild(elements[0]);
    }

    modal.setAttribute("class","modal fade");
    modal.setAttribute("style","display: none;");
}

function setValueOfInputToNull(id){
    var input = document.getElementById(id);
    input.value="";
}

function setFullNameIn(textLabel,modalLabelId){
    var label = document.getElementById(modalLabelId);
    var firstName = document.getElementById("firstName");
    var lastName = document.getElementById("lastName");
    label.innerHTML = textLabel+firstName.value+" "+lastName.value;
}
function initModalClosingByName(name,func){
    var modal = document.getElementsByClassName(name+"-close-modal");
    for (var i=0;i<modal.length;i++)
    {
        modal[i].onclick = func;
    }
}
function initModalSubmitById(id,func){
    var button = document.getElementById(id);
    button.onclick = func;
}

function getAllSelectedChecks(checkName){
    var elements = document.getElementsByName(checkName);
    var tmp = [];
    for(var i=0;i<elements.length;i++){
        if(elements[i].checked==true) tmp.push(elements[i]);
    }
    if(tmp.length==0)return null;
    return tmp;
}
