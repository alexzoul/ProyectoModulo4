package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@ViewScoped
public class AdminBooksBean implements Serializable
{
    private ArrayList<Book> listBooks;
    private Integer row;
    
    public AdminBooksBean() 
    {
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Administrador");
    }
    
    @PostConstruct
    public void init() 
    {        
        BookDAO bookDAO = new BookDAO();
        listBooks = bookDAO.findAll();
        row = 0;
    }

    public String updateBook(Book book)
    {
        BookDAO bookDAO = new BookDAO();
        if(bookDAO.updateById(book) == true)
        {
            return "/private/Users.jsf?faces-redirect=true";
        }
        return null;
    }
    
    public ArrayList<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<Book> listBooks) {
        this.listBooks = listBooks;
    }

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
