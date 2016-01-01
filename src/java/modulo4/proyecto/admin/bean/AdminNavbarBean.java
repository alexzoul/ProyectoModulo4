package modulo4.proyecto.admin.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@RequestScoped
public class AdminNavbarBean 
{
    Boolean session;
    
    public AdminNavbarBean() 
    {
        session = false;
        SessionBean sessionService = new SessionBean();
        if(sessionService.getIdSession("Administrador") != 0)
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
