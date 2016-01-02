package modulo4.proyecto.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import modulo4.proyecto.model.User;

@ManagedBean
@RequestScoped
public class SessionBean
{
    public void checkSession(String roleType) 
    {
        String redirectUrl = "/public/Login.jsf";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        if("Administrador".equals(roleType))
        {
            redirectUrl = "/private/Login.jsf";
        }

        if(httpSession.getAttribute(roleType +  "_id") == null)
        {
            try
            {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String ctxPath = ((ServletContext) externalContext.getContext()).getContextPath();
                externalContext.redirect(ctxPath + redirectUrl);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void initSession (User user)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession currentSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        currentSession.setAttribute(user.getRole().getType() + "_id", user.getId());
    }
        
    public int getIdSession(String roleType) 
    {
        int idSession = 0;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        if(httpSession.getAttribute(roleType + "_id") != null)
        {
            idSession = Integer.parseInt(httpSession.getAttribute(roleType + "_id").toString());
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
}
