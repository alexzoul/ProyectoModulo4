package modulo4.proyecto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable
{
    private String email;
    private String password;
    private boolean error = false;

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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    public String initSession()
    {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser(email, password);
        
        if(user == null)
        {
            error = true;
            return "/public/Login.jsf";
        }
        else
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName() + " " + user.getPaternal_name());
            session.setAttribute("email", user.getEmail());
            return "/public/MyAccount.jsf?faces-redirect=true";
        }
    }
    
    public String closeSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        return "/public/Home.jsf?faces-redirect=true";
    }
}
