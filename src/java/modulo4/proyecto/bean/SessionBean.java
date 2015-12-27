package modulo4.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionBean implements Serializable
{
    private ArrayList books;
    
    public SessionBean () {        
    }
    
    public String addBook(int id) {
        System.out.println("add libro: " + id);
        return "/public/Home.jsf?faces-redirect=true";
    }
}
