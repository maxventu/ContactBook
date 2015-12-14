window.onload = load;
var ContactBook = {
    lastId:0
};
function load(){
    console.log("adding listeners");
    setLastTelephoneId();
    var button = document.getElementById("telephoneAddButton");
    button.onclick = createTelephoneModal;
    button = document.getElementById("telephoneDelete");
    button.onclick = deleteTelephone;
    button = document.getElementById("telephoneEditButton");
    button.onclick = editTelephoneModal;
    button = document.getElementById("telephoneModalApply");
    button.onclick = submitTelephone;

    var modal = document.getElementsByClassName("telephone-close-modal");
    for (var i=0;i<modal.length;i++)
    {
        modal[i].onclick = closeTelephoneModal;
        console.log( modal[i].onclick);
    }
}

function openTelephoneModal(){
    console.log("show telephone modal");
    document.body.setAttribute("class","modal-open");
    document.body.setAttribute("style","padding-right: 17px;");

    var el = document.createElement("span");
    el.setAttribute("id","deletableElement");
    el.setAttribute("class","modal-backdrop fade in");
    document.body.appendChild(el);

    var telephoneModal = document.getElementById("telephoneModal");
    telephoneModal.setAttribute("class","modal fade in");
    telephoneModal.setAttribute("style","display: block;");
}
function closeTelephoneModal(){
    console.log("hide telephone modal");
    document.body.removeAttribute("class");
    document.body.removeAttribute("style");
    var telephoneModal = document.getElementById("telephoneModal");

    var el = document.getElementById("deletableElement");
    document.body.removeChild(el);

    telephoneModal.setAttribute("class","modal fade");
    telephoneModal.setAttribute("style","display: none;");

    reinitTelephoneModal();
}

function deleteTelephone(){
    var deletedTelephonesContainer = document.getElementById("deletedTelephones");
    var allSelected = getAllSelectedChecks("telephoneCheckbox");

    for( var i = 0; i < allSelected.length; i++ )
        deleteTelephone(allSelected[i]);

    function deleteTelephone(element){
        var id = getTelephoneId(element);
        deletedTelephonesContainer.appendChild(createDeleteHistory(id));
        var tr = document.getElementById("tel_id_"+id);
        var table = tr.parentNode;
        table.removeChild(tr);

        function createDeleteHistory(elementId){
            var el = document.createElement("input");
            el.setAttribute("type", "hidden");
            el.setAttribute("name","deletedTelephone");
            el.setAttribute("value",elementId);
            return el;
        }
        function getTelephoneId(checkbox){
            return checkbox.id.replace("tel_check_","");
        }
    }
}
function editTelephoneModal(){
    setFullNameIn("telephoneModalLabel");
    var id=getFirstSelectedTelephoneId("telephoneCheckbox");
    if(id!=null)
        initTelephoneModalFields(id);
    openTelephoneModal();
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
    setFullNameIn("telephoneModalLabel");
    openTelephoneModal();
}

function submitTelephone(){

    var elem = document.getElementById("telephoneModal_id");
    var telephoneId = elem.value;
/*
    var typeInModal = document.getElementById("telephoneModal_id");
    var sel=document.getElementById("telephoneModal_type_select");
    typeInModal.setAttribute("value",sel.value);*/

    if(telephoneId != "" && telephoneId != null && telephoneId!=undefined)
        updateTelephone(telephoneId);
    else createNewTelephone();

    closeTelephoneModal();

    function updateTelephone(telephoneId) {
        updateTelephone();
        function updateTelephone(){
            //setInfoTelephoneToNew(telephoneId,"check");
            var country_code = setInfoTelephoneToNew(telephoneId,"country_code");
            var operator_code = setInfoTelephoneToNew(telephoneId,"operator_code");
            var number = setInfoTelephoneToNew(telephoneId,"number");
            var type = setInfoTelephoneToNew(telephoneId,"type_select");
            var comment = setInfoTelephoneToNew(telephoneId,"comment");
            var str = "+"+country_code.value+"("+operator_code.value+")"+number.value;
            var parent = country_code.parentNode;
            var tds = parent.getElementsByTagName("td");

            tds[1].innerHTML = str;
            var typeSel = document.getElementById("telephoneModal_type_select");
            tds[2].innerHTML = typeSel[typeSel.selectedIndex].text;
            tds[3].innerHTML = comment.value;

            function setInfoTelephoneToNew(telephoneId,fieldType){
                var inputInModal = document.getElementById("telephoneModal_"+fieldType);
                var inputInTable = document.getElementById("tel_"+fieldType+"_"+telephoneId);
                inputInTable.value = inputInModal.value;
                return inputInTable;
            }
        }
    }

    function createNewTelephone() {
        ContactBook.lastId= 1+parseInt(ContactBook.lastId);
        var newId = ContactBook.lastId;

        var newTableRow = document.createElement("tr");
        newTableRow.id = "tel_id_"+newId;

        var td = document.createElement("td");
        var input = document.createElement("input");
        input.setAttribute("name","telephoneCheckbox");
        input.setAttribute("type","checkbox");
        input.id = "tel_check_"+newId;
        td.appendChild(input);
        newTableRow.appendChild(td);

        var country_code = createInput(newId,"country_code");
        var operator_code = createInput(newId,"operator_code");
        var number = createInput(newId,"number");
        var type = createInput(newId,"type_select");
        var comment = createInput(newId,"comment");

        td = document.createElement("td");
        td.innerHTML = "+"+country_code.value+"("+operator_code.value+")"+number.value;
        newTableRow.appendChild(td);

        td = document.createElement("td");
        var sel = document.getElementById("telephoneModal_type_select");
        td.innerHTML = sel[sel.selectedIndex].text;
        newTableRow.appendChild(td);

        td = document.createElement("td");
        td.innerHTML = comment.value;
        newTableRow.appendChild(td);

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
            input.id = "tel_"+fieldType+"_"+telId;
            var inputInModal = document.getElementById("telephoneModal_"+fieldType);
            input.value = inputInModal.value;
            return input;
        }
        function createTelephoneNewHistory(elementId){
            var el = document.createElement("input");
            el.setAttribute("type", "hidden");
            el.setAttribute("name","newTelephone");
            el.setAttribute("value",elementId);
            return el;
        }
        function initNewTelephone(){
            var country_code = setInfoTelephoneToNew(newId,"country_code");
            var operator_code = setInfoTelephoneToNew(newId,"operator_code");
            var number = setInfoTelephoneToNew(newId,"number");
            var type = setInfoTelephoneToNew(newId,"type");
            var comment = setInfoTelephoneToNew(newId,"comment");
            var tds = parent.getElementsByTagName("td");
            var str = "+"+country_code.value+"("+operator_code.value+")"+number.value;
            tds[1].innerHTML = str;
            var selection = document.getElementById("telephoneModal_type_select");
            str = selection[selection.selectedIndex].text;
            tds[2].innerHTML = str;
            tds[3].innerHTML = comment.value;
            function setInfoTelephoneToNew(telId,fieldType){
                var inputInModal = document.getElementById("telephoneModal_"+fieldType);
                var inputInTable = document.getElementById("tel_"+fieldType+"_new");
                inputInTable.id = "tel_"+fieldType+"_"+telId;
                inputInTable.value = inputInModal.value;
                return inputInTable;
            }
        }
    }
}

function setFullNameIn(modalLabelId){
    var label = document.getElementById(modalLabelId);
    var firstName = document.getElementById("firstName");
    var lastName = document.getElementById("lastName");
    label.innerHTML = "New telephone for "+firstName.value+" "+lastName.value;
}
function reinitTelephoneModal(){
    setValueInputToNull("telephoneModal_country_code");
    setValueInputToNull("telephoneModal_operator_code");
    setValueInputToNull("telephoneModal_number");
    setValueInputToNull("telephoneModal_comment");
    var sel = document.getElementById("telephoneModal_type_select");
    sel.selectedIndex = 0;
    setValueInputToNull("telephoneModal_id");
    function setValueInputToNull(id){
        var input = document.getElementById(id);
        input.value=null;
    }
}

function initTelephoneModalFields(telephoneId){
    setInfoTelephoneToModal(telephoneId,"country_code");
    setInfoTelephoneToModal(telephoneId,"operator_code");
    setInfoTelephoneToModal(telephoneId,"number");
    setInfoTelephoneToModal(telephoneId,"comment");
    setInfoTelephoneToModal(telephoneId,"type_select");
    setInfoTelephoneToModal(telephoneId,"id");

    var modal = document.getElementById("telephoneModal_id");
    modal.value = telephoneId;

    function setInfoTelephoneToModal(telephoneId,fieldType){
        var inputInModal = document.getElementById("telephoneModal_"+fieldType);
        var inputInTable = document.getElementById("tel_"+fieldType+"_"+telephoneId);
        inputInModal.value = inputInTable.value;
    }
}

function getAllSelectedChecks(checkName){
    var elements = document.getElementsByName(checkName);
    var tmp = [];
    if(elements.length==0)return null;
    for(var i=0;i<elements.length;i++){
        if(elements[i].checked==true) tmp.push(elements[i]);
    }
    return tmp;
}
function setLastTelephoneId(){
    var elements = document.getElementsByName("telephoneCheckbox");
    if(elements.length>0)
    ContactBook.lastId = parseInt(elements[elements.length-1].id.replace("tel_check_",""));
    else ContactBook.lastId = 0;
}

function getSelectedTelephoneTypeModal(optionsName)
{
    var options = document.getElementsByName(optionsName);

    return options[options.selectedIndex];
}