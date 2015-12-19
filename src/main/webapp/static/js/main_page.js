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
    var chosenElements = document.getElementsByName("choseContactsCheckbox");
    for (var i=0;i<chosenElements.length;i++){
        if(chosenElements[i].checked==true)
        addContactToContainer(choseContactsContainer,chosenElements[i].value);
    }
}
function addContactToContainer(container,contactId){
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","choseContacts");
    input.setAttribute("value",contactId);
    return input;
    container.appendChild(input);
}

function setSubmitOnSelection(){
    var numbOfElements = document.getElementById("numberOfElements");
}

