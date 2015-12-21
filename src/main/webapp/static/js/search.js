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
        alert("This date doesn't exist. The format of good date is: yyyy-mm-dd");
        return false;
    }
    return true;
}

function isMatchesPattern(value, pattern) {
    if(value != undefined && pattern.test(value))
        return true;
}