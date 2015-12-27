package modulo4.proyecto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.model.Book;

@ManagedBean
@ViewScoped
public class BookDetail implements Serializable
{   
    private String id;
    private Book currentBook;
    
    public BookDetail () 
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
