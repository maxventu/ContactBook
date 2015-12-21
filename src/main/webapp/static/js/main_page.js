window.onload = init;
function init(){
    setFunctionToVariableOnClick(addItemsToContainer,"accountDelete");
    setFunctionToVariableOnClick(addItemsToContainer,"sendEmailButton");
}

function setFunctionToVariableOnClick(func,variableId){
    var delButton = document.getElementById(variableId);
    delButton.onclick = func;
}

function addItemsToContainer(){
    var choseContactsContainer = document.getElementById("choseContacts");
    choseContactsContainer.innerHTML = "";
    var chosenElements = getCheckedElementsByName("choseContactsCheckbox");
    if (chosenElements.length==0)return false;
    for (var i=0;i<chosenElements.length;i++){
        addContactToContainer(choseContactsContainer,chosenElements[i]);
    }
}

function getCheckedElementsByName(checkName){
    var chosenElements = document.getElementsByName(checkName);
    var allChecked=[];
    for (var i=0;i<chosenElements.length;i++){
        if(chosenElements[i].checked==true)
        allChecked.push(chosenElements[i].value);
    }
    return allChecked;
}
function addContactToContainer(container,contactId){
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","choseContacts");
    input.setAttribute("value",contactId);
    container.appendChild(input);
    return input;
}

function setSubmitOnSelection(){
    var numbOfElements = document.getElementById("numberOfElements");
}

