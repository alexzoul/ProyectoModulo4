package modulo4.proyecto.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;

@ManagedBean
@ViewScoped
public class RegisterBean 
{
    private String name;
    private String paternal_name;
    private String maternal_name;
    private String email;
    private String password;
    private String password_confirm;
    
    public RegisterBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternal_name() {
        return paternal_name;
    }

    public void setPaternal_name(String paternal_name) {
        this.paternal_name = paternal_name;
    }

    public String getMaternal_name() {
        return maternal_name;
    }

    public void setMaternal_name(String maternal_name) {
        this.maternal_name = maternal_name;
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

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
    
    public boolean matchPasswords() {
        if (!password.equals(password_confirm))
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("Los passwords no coinciden");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("register_form:password_confirm",  message);
            return false;
        }
        return true;
    }
    
    public String registerUser () {
        if(matchPasswords()) {
            User user = new User();
            
            user.setName(name);
            user.setPaternal_name(paternal_name);
            user.setMaternal_name(maternal_name);
            user.setEmail(email);
            user.setPassword(password);
            
            UserDAO userDAO = new UserDAO();
            if(userDAO.insert(user) == 1)
            {
                LoginBean login = new LoginBean();
                login.setEmail(email);
                login.setPassword(password);
                return login.initSession();
            }
        }
        return null;
    }
}
