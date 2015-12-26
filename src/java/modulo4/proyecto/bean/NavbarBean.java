package modulo4.proyecto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class NavbarBean implements Serializable
{
    public NavbarBean () {
    }
    
    public String myAccount()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        if (session.getAttribute("id") != null) 
        {
            return "/public/MyAccount.jsf";
        }
        return "/public/Login.jsf";
    }
    public String myCar()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        if (session.getAttribute("id") != null) 
        {
            return "/public/MyCar.jsf";
        }
        return "/public/Login.jsf";
    }
    public String closeSession()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        return "/public/Home.jsf";
    }
}
