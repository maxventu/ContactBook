
function loadAvatarMangment(){
    var button = document.getElementById("ava_image");
    button.onclick = editAvatarModal;
    initModalClosingByName("avatarEditModal",closeAvatarEditModal);
    //initModalSubmitById("avatarEditModalApply",submitEditAvatar);
    var button = document.getElementById("avatarEditModalApply");
    button.onsubmit = submitEditAvatar;
    var button = document.getElementById("hiddenAvatarFrame");
    button.onload = handleAvatarResponse;
}

function closeAvatarEditModal(){
    closeModal("avatarEditModal");
    reinitAvatarEditModal();
}

function editAvatarModal(){

    openModal("Edit avatar for ","avatarEditModal");
    var contactIdModal = document.getElementById("contactIdForAvatar");
    var contactId = document.getElementById("contact_id");
    contactIdModal.value = contactId.value;
}

function submitEditAvatar() {
        closeAvatarEditModal();
}

function reinitAvatarEditModal(){
    var types = ["file"];
    types.forEach(function(type,i,types){
        setValueOfInputToNull("avatarEditModal_"+type);
    });
}

function handleAvatarResponse(msg){
    var uploadDocument = document.getElementById("hiddenAvatarFrame").contentWindow.document;
    var loaded = uploadDocument.getElementById("avatarLoaded");
    console.log("fuck "+loaded.value);
    if(loaded!= null && loaded.value == "true"){

        var url = uploadDocument.getElementById("avatarURL");

        var ava_image = document.getElementById("ava_image");
        console.log(ava_image.value);
        ava_image.src=url.value;
        loaded.value=false;
    }
    submitEditAvatar();
}