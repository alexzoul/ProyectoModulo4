package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.model.User;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@RequestScoped
public class AdminHomeBean implements Serializable
{
    private ArrayList<User> listUser;

    public AdminHomeBean() 
    {
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Administrador");
    }
    
    @PostConstruct
    public void init()
    {   
        
    }
    
    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
}
