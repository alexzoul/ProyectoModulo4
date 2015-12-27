package modulo4.proyecto.bean;

import modulo4.proyecto.service.SessionService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.model.Office;
import modulo4.proyecto.model.User;

@ManagedBean
@SessionScoped
public class MyCarBean implements Serializable
{
    private ArrayList<Book> listBooks;
    private ArrayList<Office> listOffices;
    private Float total;
    private Integer row;
    public MyCarBean () {    
    }
    
    @PostConstruct
    public void init()
    {
        System.out.println("Inicio");
        cleanVariables();
        this.listBooks = new ArrayList<>();
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

    public ArrayList<Office> getListOffices() {
        return listOffices;
    }

    public void setListOffices(ArrayList<Office> listOffices) {
        this.listOffices = listOffices;
    }

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
    
    
    public void addListBooks (Book book)
    {
        listBooks.add(book);
        cleanVariables();
        
        for (int i = 0; i < listBooks.size(); i++)
        {
            total += listBooks.get(i).getPrice();
        }
    }
    
    public void removeBook (Book book)
    {
        listBooks.remove(book);
        cleanVariables();
        
        for (int i = 0; i < listBooks.size(); i++)
        {
            total += listBooks.get(i).getPrice();
        }
    }
    
    public void cleanVariables()
    {
        this.row = 0;
        this.total = 0.00f;
    }
    
    public String addBook(int id) 
    {
        SessionService sessionBean = new SessionService();

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
    
    public String buyBooks()
    {
        SessionService sessionBean = new SessionService();
        
        int idSession = sessionBean.getIdSession();
        if(idSession != 0)
        {
            User user = new User();
            user.setId(idSession);
            Office office = new Office();
            office.setId(1);
            RequisitionDAO requisitionDAO = new RequisitionDAO();
            if(requisitionDAO.insert(total, user, office, listBooks) != 0)
            {
                listBooks.clear();
                cleanVariables();
                
                return "/public/MyRequisitions.jsf?faces-redirect=true";
            }
        }
        return "/public/Login.jsf?faces-redirect=true";
    }
            
}
