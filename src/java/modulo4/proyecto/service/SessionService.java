package modulo4.proyecto.service;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modulo4.proyecto.model.User;

@ManagedBean
@RequestScoped
public class SessionService implements Serializable
{
    private boolean session;
  
    public SessionService () {
        this.session = false;
    }
    @PostConstruct
    public void init() {
        this.session = checkSession();
    }
       
    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public boolean checkSession() 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        return httpSession.getAttribute("id") != null;
    }
    
    public int getIdSession() 
    {
        int idSession = 0;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        if(httpSession.getAttribute("id") != null)
        {
            idSession = Integer.parseInt(httpSession.getAttribute("id").toString());
        }
        return idSession;
    }    
    
    public String closeSession() 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession currentSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        currentSession.invalidate();
        return "/public/Home.jsf?faces-redirect=true";
    }
    
    public void initSession (User user)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession currentSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        currentSession.setAttribute("id", user.getId());
        currentSession.setAttribute("name", user.getName() + " " + user.getPaternal_name());
        currentSession.setAttribute("email", user.getEmail());
    }
}
