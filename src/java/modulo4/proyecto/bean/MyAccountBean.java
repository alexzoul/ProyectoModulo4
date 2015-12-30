package modulo4.proyecto.bean;

import modulo4.proyecto.service.SessionService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;

@ManagedBean
@RequestScoped
public class MyAccountBean implements Serializable
{
    private User user;
    
    public MyAccountBean () {
    }
    
    @PostConstruct
    public void init () 
    {
        SessionService sessionBean = new SessionService();
        int idSession = sessionBean.getIdSession();
        if (idSession != 0)
        {
            UserDAO userDAO = new UserDAO();
            user = userDAO.findById(idSession);
        }
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
