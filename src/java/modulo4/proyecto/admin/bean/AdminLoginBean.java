package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;
import modulo4.proyecto.service.SessionService;

@ManagedBean
@SessionScoped
public class AdminLoginBean implements Serializable
{
    private String email;
    private String password;
    
    public AdminLoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String initSession() {
        UserDAO userDAO = new UserDAO();
        User admin = userDAO.validateAdmin(email, password);
        
        if(admin != null)
        {
            SessionService sessionBean = new SessionService();
            sessionBean.initSessionAdmin(admin);
            return "/private/Home.jsf?faces-redirect=true";
        }
        else
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("Correo y/o Contraseña incorrectos");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("login_form:password",  message);
            return null;
        }
        
    }
    
}
