package modulo4.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modulo4.proyecto.dao.BookDAO;


@ManagedBean
@ViewScoped
public class HomeBean implements Serializable
{
    private ArrayList books;
    private String findText;
    
    public HomeBean() {
    }
    
    @PostConstruct
    public void init() {
        BookDAO bookDAO = new BookDAO();
        books = bookDAO.findAll();
    }
    
    public void findBook() {
        BookDAO bookDAO = new BookDAO();
        books = bookDAO.findText(findText);
    }

    public ArrayList getBooks() {
        return books;
    }

    public void setBooks(ArrayList books) {
        this.books = books;
    }

    public String getFindText() {
        return findText;
    }

    public void setFindText(String findText) {
        this.findText = findText;
    }
}
