function loadTelephoneMangment(){
    setLastTelephoneId();
    var button = document.getElementById("telephoneAddButton");
    button.onclick = createTelephoneModal;
    button = document.getElementById("telephoneDelete");
    button.onclick = deleteTelephones;
    button = document.getElementById("telephoneEditButton");
    button.onclick = editTelephoneModal;
    initModalSubmitById("telephoneModalApply",submitTelephone);
    initModalClosingByName("telephoneModal",closeTelephoneModal);
}

function openTelephoneModal(textLabel){
    openModal(textLabel,"telephoneModal");
    var country_code = document.getElementById("telephoneModal_country_code");
    country_code.focus();
}

function closeTelephoneModal(){
    closeModal("telephoneModal");
    reinitTelephoneModal();
}

function deleteTelephones(){

    var allSelected = getAllSelectedChecks("tel_check");
    for( var i = 0; i < allSelected.length; i++ )
        deleteTelephone(allSelected[i]);

    function deleteTelephone(element){
        var deletedTelephonesContainer = document.getElementById("deletedTelephones");
        var id = getTelephoneId(element);

        deletedTelephonesContainer.appendChild(createDeleteHistory(id));
        var tr = document.getElementById("tel_id_"+id);
        var table = tr.parentNode;
        table.removeChild(tr);

        function createDeleteHistory(elementId){
            var el = document.createElement("input");
            el.setAttribute("type", "hidden");
            el.setAttribute("name","deletedTelephones");
            el.setAttribute("value",elementId);
            return el;
        }
        function getTelephoneId(checkbox){
            return checkbox.id.replace("tel_check_","");
        }
    }
}

function editTelephoneModal(){
    var id=getFirstSelectedTelephoneId("tel_check");
    if(id!=null){
        initTelephoneModalFields(id);
        openTelephoneModal("Edit telephone of ");
    }
    else createTelephoneModal();

    function getFirstSelectedTelephoneId(checkName){
        var elements = document.getElementsByName(checkName);
        if(elements.length<1)return null;
        for(var i=0;i<elements.length;i++){
            if(elements[i].checked==true) return elements[i].id.replace("tel_check_","");
        }
        return null;
    }
}

function createTelephoneModal(){
    openTelephoneModal("Add new telephone to ");
}

function submitTelephone(){

    var elem = document.getElementById("telephoneModal_id");
    var telephoneId = elem.value;

    if(telephoneId != "" && telephoneId != null && telephoneId!=undefined)
        updateTelephone(telephoneId);
    else createNewTelephone();

    closeTelephoneModal();

    function updateTelephone(telephoneId) {
        var country_code = setInfoTelephoneToNew(telephoneId,"country_code");
        var operator_code = setInfoTelephoneToNew(telephoneId,"operator_code");
        var number = setInfoTelephoneToNew(telephoneId,"number");
        setInfoTelephoneToNew(telephoneId,"type_select");
        var comment = setInfoTelephoneToNew(telephoneId,"comment");
        var str = (country_code.value.toString()!="" ? "+"+country_code.value : "")+
            (operator_code.value.toString()!="" ? "("+operator_code.value+")" : "")+number.value;
        var parent = country_code.parentNode;
        var tds = parent.getElementsByTagName("td");

        tds[1].innerHTML = str;
        var typeSel = document.getElementById("telephoneModal_type");
        tds[2].innerHTML = typeSel[typeSel.selectedIndex].text;
        tds[3].innerHTML = comment.value;


        var updatedTelephonesContainer = document.getElementById("updatedTelephones");
        updatedTelephonesContainer.appendChild(createDeleteHistory(telephoneId));

        function createUpdateHistory(elementId){
            var el = document.createElement("input");
            el.setAttribute("type", "hidden");
            el.setAttribute("name","updatedTelephones");
            el.setAttribute("value",elementId);
            return el;
        }

        function setInfoTelephoneToNew(telephoneId,fieldType){
            var inputInModal = document.getElementById("telephoneModal_"+fieldType);
            var inputInTable = document.getElementById("tel_"+fieldType+"_"+telephoneId);
            inputInTable.value = inputInModal.value;
            return inputInTable;
        }


    }

    function createNewTelephone() {
        contactVariables.telephoneLastId= 1+parseInt(contactVariables.telephoneLastId);
        var newId = contactVariables.telephoneLastId;

        var newTableRow = document.createElement("tr");
        newTableRow.id = "tel_id_"+newId;

        var td = document.createElement("td");
        var input = document.createElement("input");
        input.setAttribute("name","tel_check");
        input.setAttribute("type","checkbox");
        input.id = "tel_check_"+newId;
        td.appendChild(input);
        newTableRow.appendChild(td);

        var country_code = createInput(newId,"country_code");
        var operator_code = createInput(newId,"operator_code");
        var number = createInput(newId,"number");
        var type = createInput(newId,"type");
        var comment = createInput(newId,"comment");

        td = document.createElement("td");
        td.innerHTML = (country_code.value.toString()!="" ? "+"+country_code.value : "")+
            (operator_code.value.toString()!="" ? "("+operator_code.value+")" : "")+number.value;
        newTableRow.appendChild(td);

        td = document.createElement("td");
        var sel = document.getElementById("telephoneModal_type");
        td.innerHTML = sel[sel.selectedIndex].text;
        newTableRow.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = comment.value;
        newTableRow.appendChild(td);

        var idInput = createHiddenInputForTelephones(newId,"id");
        idInput.value = newId;
        newTableRow.appendChild(idInput);
        newTableRow.appendChild(country_code);
        newTableRow.appendChild(operator_code);
        newTableRow.appendChild(number);
        newTableRow.appendChild(type);
        newTableRow.appendChild(comment);

        var telephones = document.getElementById("tel_table");
        telephones.appendChild(newTableRow);
        var newTelephonesContainer = document.getElementById("newTelephones");
        newTelephonesContainer.appendChild(createTelephoneNewHistory(newId));

        function createInput(telId,fieldType){
            var input = document.createElement("input");
            input.setAttribute("type","hidden");

            var inputInModal = document.getElementById("telephoneModal_"+fieldType);
            input.setAttribute("name","tel_"+fieldType);
            input.id = "tel_"+fieldType+"_"+telId;
            input.value = inputInModal.value;
            return input;
        }

        function createHiddenInputForTelephones(attId,fieldType){
            var input = document.createElement("input");
            input.setAttribute("type","hidden");
            input.setAttribute("name","tel_"+fieldType);
            input.id = "tel_"+fieldType+"_"+attId;
            return input;
        }

        function createTelephoneNewHistory(elementId){
            var el = document.createElement("input");
            el.setAttribute("type", "hidden");
            el.setAttribute("name","newTelephones");
            el.setAttribute("value",elementId);
            return el;
        }
    }
}

function reinitTelephoneModal(){
    var types = ["country_code","operator_code","number","comment","id"];

    types.forEach(function(type,i,types){
        setValueOfInputToNull("telephoneModal_"+type);
    });
    var sel = document.getElementById("telephoneModal_type");
    sel.selectedIndex = 0;
}

function initTelephoneModalFields(telephoneId){
    var types = ["id","country_code","operator_code","number","comment","type_select"];
    types.forEach(function(type,i,types){
        setInfoTelephoneToModal(telephoneId,type);
    });

    var modal = document.getElementById("telephoneModal_id");
    modal.value = telephoneId;

    function setInfoTelephoneToModal(telephoneId,fieldType){
        var inputInModal = document.getElementById("telephoneModal_"+fieldType);
        var inputInTable = document.getElementById("tel_"+fieldType+"_"+telephoneId);
        inputInModal.value = inputInTable.value;
    }
}


function setLastTelephoneId(){
    var elements = document.getElementsByName("tel_check");
    if(elements.length>0)
        contactVariables.telephoneLastId = parseInt(elements[elements.length-1].id.replace("tel_check_",""));
    else contactVariables.telephoneLastId = 0;
}

function validateAllTelephoneFields(){
    var isValid = true;
    var notNeeded = ["telephoneModal_country_code","telephoneModal_operator_code"];
    for(var i=0;i<notNeeded.length;i++){
        if(!validateString(notNeeded[i],0,3))isValid = false;
    }
    if(!validateString("telephoneModal_comment",0,100))isValid = false;
    if(!validateString("telephoneModal_number",5,10))isValid = false;
    return isValid;
}