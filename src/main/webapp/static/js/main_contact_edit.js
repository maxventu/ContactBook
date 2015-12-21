window.onload = load;
var contactVariables = {
    telephoneLastId:0,
    attachmentLastId:0
};

function load(){
    loadTelephoneMangment();
    loadAttachmentsManagement();
    loadAvatarMangment();
    var form = document.getElementById("data");
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

function initAllTextValidations(){
    var notNeeded = ["patronymic","nationality","webSite","email","currentWorkplace","city","country","street","house","apartment"];
    var needed = ["firstName","lastName"];
    var isValid = true;
    for(var i=0;i<notNeeded.length;i++){
        if(!validateString(notNeeded[i],0,45))isValid = false;
    }
    for(var i=0;i<needed.length;i++){
        if(!validateString(needed[i],1,45))isValid = false;
    }
    if(!validateString("postcode",0,11))isValid = false;
    if((validateString("city",1,45) || validateString("country",1,45)) && !validateString("postcode",1,11))isValid = false;
    if(!isBirthDateCorrect())isValid = false;

    return isValid;
}



function validateString(fieldId,min,max,fieldName){
    var element = document.getElementById(fieldId);
    var valid = true;
    return checkElement(element);

    function checkElement(el){
        if(el.value==null){return false;}
        if( el.value.length<min|| el.value.length>max) {
            el.parentNode.classList.add("has-error");
            alert("you've entered incorrect data in "+fieldId+", it must be from "+min+" to "+max+" length");
            return false;
        }
        else {
             el.parentNode.classList.remove("has-error");
            return true;
        }
    }
}

function isBirthDateCorrect() {
    var elem=document.getElementById('dateOfBirth');
    var birthString = elem.value;

    if(isMatchesPattern(birthString, /^(.){0}$/)) {
        return true;
    }
    if(!isMatchesPattern(birthString, /^(.){0}|[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/)) {
        alert('Date format: yyyy-mm-dd');
        return false;
    }
    var birthValue = birthString.split('-');
    var birthDate = new Date();
    birthDate.setFullYear(birthValue[0], (birthValue[1] - 1), birthValue[2]);
    if((birthValue[0] != birthDate.getFullYear()) ||  (birthValue[1] - 1 != birthDate.getMonth())
        || (birthValue[2] != birthDate.getDate())) {
        alert("This date doesn't exist.");
        return false;
    }
    var currentDate = new Date();
    if(birthDate > currentDate) {
        alert('your birth can\'t be after today!');
        return false;
    }
    if(birthDate.getFullYear() < currentDate.getFullYear() - 150) {
        alert('we hope, that you have a long life, but we doubt about your age!');
        return false;
    }
    return true;
}

function isMatchesPattern(value, pattern) {
    if(value != undefined && pattern.test(value))
        return true;
}

function createInputForHistory(elementValue,elementName){
    var el = document.createElement("input");
    el.setAttribute("type", "hidden");
    el.setAttribute("name",elementName);
    el.setAttribute("value",elementValue);
    return el;
}