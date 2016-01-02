package modulo4.proyecto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.service.BookService;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@ViewScoped
public class BookDetail implements Serializable
{   
    @ManagedProperty("#{serviceBook}")
    private BookService serviceBook;
    
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
        else
        {
            currentBook = new Book();
            try
            {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String ctxPath = ((ServletContext) externalContext.getContext()).getContextPath();
                externalContext.redirect(ctxPath + "/public/Home.jsf");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }  

    public String addBook(int id) 
    {
        if(id != 0)
        {
            SessionBean sessionService = new SessionBean();

            if (sessionService.getIdSession("Cliente") != 0) 
            {
                serviceBook.addBook(id);   
                return "/public/MyCar.jsf?faces-redirect=true";
            }
            else 
            {
                return "/public/Login.jsf?faces-redirect=true";
            }
        }
        return null;
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

    public BookService getServiceBook() {
        return serviceBook;
    }

    public void setServiceBook(BookService serviceBook) {
        this.serviceBook = serviceBook;
    }
}
