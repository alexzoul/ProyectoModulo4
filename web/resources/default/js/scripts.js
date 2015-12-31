$( document ).ready(function() 
{
    var path = window.location.pathname;
    var idElement = getIdNavbarElement(path);
    if(idElement !== null)
    {
        var element = document.querySelector(idElement);
        element.className = "active";
    }
});

function getIdNavbarElement(path)
{
    switch (path)
    {
        case '/ProyectoModulo4/public/Home.jsf':
            return '#li_home';
            break;
        case '/ProyectoModulo4/public/BookCatalog.jsf':
            return '#li_book_catalog';
            break;
        case '/ProyectoModulo4/public/Office.jsf':
            return '#li_office';
            break;
        case '/ProyectoModulo4/public/Login.jsf':
            return '#li_login';
            break;
        case '/ProyectoModulo4/public/Register.jsf':
            return '#li_register';
            break;
        case '/ProyectoModulo4/public/MyAccount.jsf':
            return '#li_myaccount';
            break;
        case '/ProyectoModulo4/public/MyRequisitions.jsf':
            return '#li_myrequisitions';
            break;
        case '/ProyectoModulo4/public/MyCar.jsf':
            return '#li_mycar';
            break;
        default:
            return null;
            break; 
    }
}