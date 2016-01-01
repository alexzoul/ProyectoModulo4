package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@ViewScoped
public class AdminBookEditBean implements Serializable
{
    private String id;
    private Book currentBook;
    
    public AdminBookEditBean() 
    {
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Administrador");
    }

    @PostConstruct
    public void init()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        id = (String) facesContext.getExternalContext().getRequestParameterMap().get("id");
        if(id != null)
        {
            BookDAO bookDAO = new BookDAO();
            Book book = bookDAO.findById(Integer.parseInt(id));
            currentBook = book;   
        }
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }
    
    public String update()
    {
        BookDAO bookDAO = new BookDAO();
        if(bookDAO.updateById(currentBook))
        {
            return "/private/Books.jsf?faces-redirect=true";
        }
        return null;
    }
}
