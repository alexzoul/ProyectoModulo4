package modulo4.proyecto.service;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.model.Book;

@ManagedBean(name = "serviceBook", eager = true)
@SessionScoped
public class BookService implements Serializable
{
    private ArrayList<Book> listBooks;
    private Float total;
    
    public BookService() 
    {
        listBooks = new ArrayList<Book>();
        total = 0.0f;
    }
    
    public void removeBook (Book book)
    {
        listBooks.remove(book);
        calcTotal();
    }
     
    public void addBook(Integer id) 
    {
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.findById(id);
        listBooks.add(book);
        calcTotal();
    }
    
    public void calcTotal()
    {
        total = 0.0f;
        for (int i = 0; i < listBooks.size(); i++)
        {
            total += listBooks.get(i).getPrice();
        }
    }
    
    public ArrayList<Book> getListBooks ()
    {
        return listBooks;
    }

    public void setListBooks(ArrayList<Book> listBooks) {
        this.listBooks = listBooks;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}