package modulo4.proyecto.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.UserDAO;
import modulo4.proyecto.model.User;

@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable
{
    private String name;
    private String paternal_name;
    private String maternal_name;
    private String email;
    private String phone_number;
    private String password;
    
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String registerUser () {
        User user = new User();

        user.setName(name);
        user.setPaternal_name(paternal_name);
        user.setMaternal_name(maternal_name);
        user.setEmail(email);
        user.setPhone_number(phone_number);
        user.setPassword(password);
        UserDAO userDAO = new UserDAO();

        if(userDAO.checkEmail(email) == false)
        {
            if(userDAO.insert(user) == 1)
            {
                LoginBean login = new LoginBean();
                login.setEmail(email);
                login.setPassword(password);
                return login.initSession();
            }
        }
        else
        {
            FacesMessage message = new FacesMessage();
            message.setDetail("El correo ya se encuentra registrado");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("register_form:email",  message);
        }
        
        return null;
    }
}
