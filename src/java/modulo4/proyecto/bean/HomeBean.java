package modulo4.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modulo4.proyecto.dao.BookHasRequisitonDAO;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable
{
    private ArrayList listBooks;
    
    public HomeBean() {
    }
    
    @PostConstruct
    public void init() {
        BookHasRequisitonDAO bookHasRequisitonDAO = new BookHasRequisitonDAO();
        listBooks = bookHasRequisitonDAO.findMoreSales();
    }

    public ArrayList getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList listBooks) {
        this.listBooks = listBooks;
    }
}
