package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;
import modulo4.proyecto.service.SessionService;

@ManagedBean
@RequestScoped
public class AdminUsersBean implements Serializable
{
    private ArrayList<User> listUser;
    private Integer row;
    
    public AdminUsersBean() 
    {
        SessionService sessionService = new SessionService();
        sessionService.checkSessionAdmin();
    }
    
    @PostConstruct
    public void init() 
    {        
        UserDAO userDAO = new UserDAO();
        listUser = userDAO.findAll();
        row = 0;
    }
    
    public String delete(Integer id)
    {
        UserDAO userDAO = new UserDAO();
        if(userDAO.delete(id))
        {
            return "/private/Users.jsf?faces-redirect=true";
        }
        else
        {
            return "error";
        }
    }

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
