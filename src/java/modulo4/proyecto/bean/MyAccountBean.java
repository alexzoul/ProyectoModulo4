package modulo4.proyecto.bean;

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
        SessionBean sessionBean = new SessionBean();
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
