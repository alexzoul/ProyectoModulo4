package modulo4.proyecto.bean;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.model.Book;

@ManagedBean
@SessionScoped
public class DataSessionBean 
{
    private ArrayList<Book> listBooks;
    private Float total;
    
    public DataSessionBean () {    
    }
    
    @PostConstruct
    public void init()
    {
        System.out.println("Inicio");
        this.listBooks = new ArrayList<>();
        this.total = 0.00f;
    }
    
    public ArrayList<Book> getListBooks() 
    {
        return listBooks;
    }

    public void setListBooks(ArrayList<Book> listBooks) 
    {
        this.listBooks = listBooks;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
    public void addListBooks (Book book)
    {
        this.listBooks.add(book);
        this.total = 0.00f;
        for (int i = 0; i < listBooks.size(); i++)
        {
            total += listBooks.get(i).getPrice();
        }
    }
    
    public void removeBook (Book book)
    {
        this.listBooks.remove(book);
        this.total = 0.00f;
        for (int i = 0; i < listBooks.size(); i++)
        {
            total += listBooks.get(i).getPrice();
        }
    }
    
    public String addBook(int id) 
    {
        SessionBean sessionBean = new SessionBean();

        if (sessionBean.checkSession()) 
        {
            BookDAO bookDAO = new BookDAO();
            Book book = bookDAO.findById(id);
            addListBooks(book);
            return "/public/MyCar.jsf?faces-redirect=true";
        }
        else 
        {
            return "/public/Login.jsf?faces-redirect=true";
        }
    }
            
}
