package modulo4.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modulo4.proyecto.dao.BookDAO;

@ManagedBean
@ViewScoped
public class BookCatalog implements Serializable
{
    private ArrayList listBooks;
    private String findText;
    
    public BookCatalog() {
    }
    
    @PostConstruct
    public void init() {
        BookDAO bookDAO = new BookDAO();
        listBooks = bookDAO.findAll();
    }
    
    public void findBook() {
        BookDAO bookDAO = new BookDAO();
        listBooks = bookDAO.findText(findText);
    }

    public String getFindText() {
        return findText;
    }

    public void setFindText(String findText) {
        this.findText = findText;
    }

    public ArrayList getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList listBooks) {
        this.listBooks = listBooks;
    }
}
