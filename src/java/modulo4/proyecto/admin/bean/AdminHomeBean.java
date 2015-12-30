package modulo4.proyecto.admin.bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.model.User;
import modulo4.proyecto.service.SessionService;

@ManagedBean
@RequestScoped
public class AdminHomeBean 
{
    private ArrayList<User> listUser;

    public AdminHomeBean() {
        SessionService sessionService = new SessionService();
        sessionService.checkSessionAdmin();
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
