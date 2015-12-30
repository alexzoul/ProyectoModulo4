package modulo4.proyecto.service;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import modulo4.proyecto.model.User;

@ManagedBean
@RequestScoped
public class SessionService implements Serializable
{
    private Boolean session;
  
    public SessionService () {
        this.session = false;
    }
    @PostConstruct
    public void init() {
        this.session = checkSession();
    }
       
    public Boolean getSession() {
        return session;
    }

    public void setSession(Boolean session) {
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
    }
    
    public void initSessionAdmin (User admin)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession currentSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        currentSession.setAttribute("adminId", admin.getId());
    }
    
    public void checkSessionAdmin() 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        if(httpSession.getAttribute("adminId") == null)
        {
            try
            {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String ctxPath = ((ServletContext) externalContext.getContext()).getContextPath();
                externalContext.redirect(ctxPath + "/private/Login.jsf");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public String closeSessionAdmin() 
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession currentSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        currentSession.invalidate();
        return "/private/Login.jsf?faces-redirect=true";
    }
    
    public int getAdminIdSession() 
    {
        int idSession = 0;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        if(httpSession.getAttribute("adminId") != null)
        {
            idSession = Integer.parseInt(httpSession.getAttribute("adminId").toString());
        }
        return idSession;
    } 
}
