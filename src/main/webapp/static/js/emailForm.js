window.onload = init();
function init(){
    var template = document.getElementById("template_select");
    template.onchange = changeTemplateText;
}

function changeTemplateText(){
    var template = document.getElementById("template_select");
    var textArea = document.getElementById("template_text");
    var templateText = document.getElementById("template_text_"+template.value);
    textArea.innerHTML = templateText.value;
}