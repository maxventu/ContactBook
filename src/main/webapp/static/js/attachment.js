
function loadAttachmentsManagement(){
    setLastAttachmentId();
    var button = document.getElementById("attachmentAddButton");
    button.onclick = createAttachmentModal;
    button = document.getElementById("attachmentDelete");
    button.onclick = deleteAttachments;
    button = document.getElementById("attachmentEditButton");
    button.onclick = editAttachmentModal;
    initModalClosingByName("attachmentAddModal",closeAttachmentAddModal);
    initModalClosingByName("attachmentEditModal",closeAttachmentEditModal);
    initModalSubmitById("attachmentAddModalApply",submitAddAttachment);
    initModalSubmitById("attachmentEditModalApply",submitEditAttachment);
    var but = document.getElementById("hiddenAttachmentFrame");
    but.onload = handleAttachmentResponse;
}

function createAttachmentModal(){
    openModal("New attachment for ","attachmentAddModal");
}

function deleteAttachments(){

    var allSelected = getAllSelectedChecks("att_check");
    for( var i = 0; i < allSelected.length; i++ )
        deleteAttachment(allSelected[i]);

    function deleteAttachment(element){

        var deletedAttachmentsContainer = document.getElementById("deletedAttachments");
        var id = getAttachmentId(element);

        deletedAttachmentsContainer.appendChild(createInputForHistory(id,"deletedAttachments"));
        var tr = document.getElementById("att_id_"+id);
        var table = tr.parentNode;
        table.removeChild(tr);

        function getAttachmentId(checkbox){
            return checkbox.id.replace("att_check_","");
        }
    }
}

function closeAttachmentAddModal(){
    closeModal("attachmentAddModal");

}

function closeAttachmentEditModal(){
    closeModal("attachmentEditModal");
    reinitAttachmentEditModal();
}

function editAttachmentModal(){

    var id=getFirstSelectedAttachmentId("att_check");
    if(id!=null){
        initAttachmentEditModalFields(id);
        openModal("Edit attachment for ","attachmentEditModal");
    }

    function getFirstSelectedAttachmentId(checkName){
        var elements = document.getElementsByName(checkName);
        if(elements.length<1)return null;
        for(var i=0;i<elements.length;i++){
            if(elements[i].checked==true) return elements[i].id.replace("att_check_","");
        }
        return null;
    }
}

function submitEditAttachment() {

    var modal_id = document.getElementById("attachmentEditModal_id");
    var attId = modal_id.value;

    var valid = validateAllAttachmentEditModalFields();
    if(!valid) return false;

    if(attId != "" && attId != null && attId!=undefined)
        mainLogic(attId);
    function mainLogic(attachmentId) {
        var filename = setInfoAttachmentToNew(attachmentId, "filename");
        var comment = setInfoAttachmentToNew(attachmentId, "comment");
        //var id = setInfoAttachmentToNew(attachmentId, "id");
        var parent = filename.parentNode;
        var tds = parent.getElementsByTagName("td");

        tds[1].innerHTML = filename.value;
        tds[3].innerHTML = comment.value;

        var updatedAttachmentsContainer = document.getElementById("updatedAttachments");
        updatedAttachmentsContainer.appendChild(createInputForHistory(attachmentId,"updatedAttachments"));

        closeAttachmentEditModal();
    }
    function setInfoAttachmentToNew(attachmentId,fieldType){
        var inputInModal = document.getElementById("attachmentEditModal_"+fieldType);
        var inputInTable = document.getElementById("att_"+fieldType+"_"+attachmentId);
        inputInTable.value = inputInModal.value;
        return inputInTable;
    }

}

function submitAddAttachment() {

    var valid = validateAllAttachmentAddModalFields();
    if(!valid) return false;

    contactVariables.attachmentLastId= 1+parseInt(contactVariables.attachmentLastId);
    var newId = contactVariables.attachmentLastId;

    var newTableRow = document.createElement("tr");
    newTableRow.id = "att_check_"+newId;

    var td = document.createElement("td");
    var input = document.createElement("input");
    input.setAttribute("name","att_check");
    input.setAttribute("type","checkbox");
    input.id = "att_check_" + newId;
    td.appendChild(input);
    newTableRow.appendChild(td);

    td = createTD(newId,"filename");
    newTableRow.appendChild(td);

    td = createTD(newId,"date_upload");
    td.innerHTML="now processing";
    newTableRow.appendChild(td);

    td = createTD(newId,"comment");
    newTableRow.appendChild(td);

    var idInput = createHiddenInput(newId,"id");
    idInput.value = newId;
    newTableRow.appendChild(idInput);
    newTableRow.appendChild(createHiddenInput(newId,"filename"));
    newTableRow.appendChild(createHiddenInput(newId,"date_upload"));
    newTableRow.appendChild(createHiddenInput(newId,"comment"));

    var attachments = document.getElementById("att_table");
    attachments.appendChild(newTableRow);
    var newAttachmentsContainer = document.getElementById("newAttachments");
    newAttachmentsContainer.appendChild(createInputForHistory(newId,"newAttachments"));

    closeAttachmentAddModal();
    function createHiddenInput(attId,fieldType){
        var input = document.createElement("input");
        input.setAttribute("type","hidden");
        input.setAttribute("name","att_"+fieldType);
        input.id = "att_"+fieldType+"_"+attId;
        return input;
    }

    function createTD(attId,fieldType){
        var td = document.createElement("td");
        td.id="td_"+fieldType+"_"+attId;
        return td;
    }
}

function initAttachmentEditModalFields(attachmentId){
    var types = ["id","filename","comment"];
    types.forEach(function(type,i,types){
        setInfoAttachmentToEditModal(attachmentId,type);
    });

    var modal = document.getElementById("attachmentEditModal_id");
    modal.value = attachmentId;

    function setInfoAttachmentToEditModal(attachmentId,fieldType){
        var inputInModal = document.getElementById("attachmentEditModal_"+fieldType);
        var inputInTable = document.getElementById("att_"+fieldType+"_"+attachmentId);
        inputInModal.value = inputInTable.value;
    }
}

function setLastAttachmentId(){
    var elements = document.getElementsByName("att_check");
    if(elements.length>0)
        contactVariables.attachmentLastId = parseInt(elements[elements.length-1].id.replace("att_check_",""));
    else contactVariables.attachmentLastId = 0;
}

function reinitAttachmentAddModal(){
    var types = ["id","filename","file","comment"];
    types.forEach(function(type,i,types){
        setValueOfInputToNull("attachmentAddModal_"+type);
    });
}

function reinitAttachmentEditModal(){
    var types = ["id","filename","comment"];
    types.forEach(function(type,i,types){
        setValueOfInputToNull("attachmentEditModal_"+type);
    });
}

function handleAttachmentResponse(){
    var attId = contactVariables.attachmentLastId;

    var uploadDocument = document.getElementById("hiddenAttachmentFrame").contentWindow.document;
    var loaded = uploadDocument.getElementById("attachmentLoaded");
    if(loaded!= null && loaded.value == "true"){

        initInputAndTD(attId,"comment","attachmentComment");
        initInputAndTD(attId,"date_upload","attachmentDate");
        initInputAndTD(attId,"filename","attachmentFileName");
        loaded.value=false;
    }
    reinitAttachmentAddModal();
    function initInputAndTD(id,type,name){
        var comment_input = document.getElementById("att_"+type+"_"+attId);
        var comment_td = document.getElementById("td_"+type+"_"+attId);
        var comment = uploadDocument.getElementById(name);
        comment_input.value=comment.value;
        comment_td.innerHTML=comment.value;
    }
    function initInput(id,type,name){
        var comment_input = document.getElementById("att_"+type+"_"+attId);
        var comment = uploadDocument.getElementById(name);
        comment_input.value=comment.value;
    }

}
function validateAllAttachmentAddModalFields(){
    var isValid = true;
    if(!validateString("attachmentAddModal_comment",0,100))isValid = false;
    if(!validateString("attachmentAddModal_filename",1,45))isValid = false;
    if(!validateString("attachmentAddModal_file",1,1000))isValid = false;
    return isValid;
}
function validateAllAttachmentEditModalFields(){
    var isValid = true;
    if(!validateString("attachmentAddModal_comment",0,100))isValid = false;
    if(!validateString("attachmentAddModal_filename",1,45))isValid = false;
    return isValid;
}