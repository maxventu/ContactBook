
function loadAttachmentsMangment(){
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
}

function createAttachmentModal(){
    openModal("New attachment for ","attachmentAddModal");
}

function deleteAttachments(){

    var allSelected = getAllSelectedChecks("attachmentCheckbox");
    for( var i = 0; i < allSelected.length; i++ )
        deleteAttachment(allSelected[i]);

    function deleteAttachment(element){

        var deletedAttachmentsContainer = document.getElementById("deletedAttachments");
        var id = getAttachmentId(element);

        deletedAttachmentsContainer.appendChild(createDeleteHistory(id));
        var tr = document.getElementById("att_id_"+id);
        var table = tr.parentNode;
        table.removeChild(tr);

        function createDeleteHistory(elementId){
            var el = document.createElement("input");
            el.setAttribute("type", "hidden");
            el.setAttribute("name","deletedAttachment");
            el.setAttribute("value",elementId);
            return el;
        }
        function getAttachmentId(checkbox){
            return checkbox.id.replace("att_check_","");
        }
    }
}

function closeAttachmentAddModal(){
    closeModal("attachmentAddModal");
    reinitAttachmentAddModal();
}

function closeAttachmentEditModal(){
    closeModal("attachmentEditModal");
    reinitAttachmentEditModal();
}

function editAttachmentModal(){

    var id=getFirstSelectedAttachmentId("attachmentCheckbox");
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

    if(attId != "" && attId != null && attId!=undefined)
        mainLogic(attId);
    function mainLogic(attachmentId) {
        var filename = setInfoAttachmentToNew(attachmentId, "filename");
        var comment = setInfoAttachmentToNew(attachmentId, "comment");
        var parent = filename.parentNode;
        var tds = parent.getElementsByTagName("td");

        tds[1].innerHTML = filename.value;
        tds[3].innerHTML = comment.value;

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
    ContactBook.attachmentLastId= 1+parseInt(ContactBook.attachmentLastId);
    var newId = ContactBook.attachmentLastId;

    var newTableRow = document.createElement("tr");
    newTableRow.id = "att_id_"+newId;

    var td = document.createElement("td");
    var input = document.createElement("input");
    input.setAttribute("name","attachmentCheckbox");
    input.setAttribute("type","checkbox");
    input.id = "att_check_"+newId;
    td.appendChild(input);
    newTableRow.appendChild(td);

    var filename = createSpecificInput(newId,"filename");
    var file = createSpecificInput(newId,"file");
    var comment = createSpecificInput(newId,"comment");
    var date = createInputWithCurrentDate(newId,"date_upload");
    var thisDate = new Date(date.value);

    td = document.createElement("td");
    td.innerHTML = filename.value;
    newTableRow.appendChild(td);

    td = document.createElement("td");
    td.innerHTML = thisDate.toLocaleTimeString() +" "+ thisDate.toLocaleDateString();
    newTableRow.appendChild(td);

    td = document.createElement("td");
    td.innerHTML = comment.value;
    newTableRow.appendChild(td);

    newTableRow.appendChild(filename);
    newTableRow.appendChild(file);
    newTableRow.appendChild(date);
    newTableRow.appendChild(comment);

    var attachments = document.getElementById("att_table");
    attachments.appendChild(newTableRow);
    var newAttachmentsContainer = document.getElementById("newAttachments");
    newAttachmentsContainer.appendChild(createAttachmentNewHistory(newId));

    closeAttachmentAddModal();
    function createInputWithCurrentDate(attId,fieldType){
        var input = createHiddenInput(attId,fieldType);
        var currentDate = new Date();
        input.value = currentDate.toISOString();
        return input;
    }
    function createSpecificInput(attId,fieldType){
        var input = createHiddenInput(attId,fieldType);
        var inputInModal = document.getElementById("attachmentAddModal_"+fieldType);
        input.value = inputInModal.value;
        return input;
    }

    function createHiddenInput(attId,fieldType){
        var input = document.createElement("input");
        input.setAttribute("type","hidden");
        input.id = "att_"+fieldType+"_"+attId;
        return input;
    }

    function createAttachmentNewHistory(elementId){
        var el = document.createElement("input");
        el.setAttribute("type", "hidden");
        el.setAttribute("name","newAttachment");
        el.setAttribute("value",elementId);
        return el;
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
    var elements = document.getElementsByName("attachmentCheckbox");
    if(elements.length>0)
        ContactBook.attachmentLastId = parseInt(elements[elements.length-1].id.replace("att_check_",""));
    else ContactBook.attachmentLastId = 0;
}

///
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