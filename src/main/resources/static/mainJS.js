
/* https://www.baeldung.com/spring-security-login-error-handling-localization*/
function validate() {

    if (document.f.username.value === "" && document.f.password.value === "") {
    alert("Brugernavn og kodeord skal indtastes");
    document.f.username.focus();
    return false;
    }
    if (document.f.username.value === "") {
    alert("Brugernavn skal indtastes");
    document.f.username.focus();
    return false;
    }
    if (document.f.password.value === "") {
    alert("Kodeord skal indtastes");
    document.f.password.focus();
    return false;
    }
}
