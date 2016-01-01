package modulo4.proyecto.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@RequestScoped
public class NavbarBean 
{
    Boolean session;
    
    public NavbarBean() 
    {
        session = false;
        SessionBean sessionService = new SessionBean();
        if(sessionService.getIdSession("Cliente") != 0)
        {
            session = true;
        }
    }
    
    public Boolean getSession() {
        return session;
    }

    public void setSession(Boolean session) {
        this.session = session;
    }
}
