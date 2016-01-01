package modulo4.proyecto.bean;

import modulo4.proyecto.session.SessionBean;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable
{
    private String email;
    private String password;

    public LoginBean() {
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
    
    public String initSession()
    {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.checkUser(email, password, "Cliente");
        
        if(user == null)
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("Correo y/o Contraseña incorrectos");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("login_form:password",  message);
            return null;
        }
        else
        {
            SessionBean sessionBean = new SessionBean();
            sessionBean.initSession(user);
            return "/public/MyAccount.jsf?faces-redirect=true";
        }
    }
}
