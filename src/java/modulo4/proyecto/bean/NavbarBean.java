package modulo4.proyecto.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class NavbarBean implements Serializable
{
    private boolean session;
    
    @PostConstruct
    public void init() {
        this.session = validateSession();
    }
    
    public NavbarBean () {
        this.session = false;
    }
        
    public boolean validateSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
        return httpSession.getAttribute("id") != null;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }
}
