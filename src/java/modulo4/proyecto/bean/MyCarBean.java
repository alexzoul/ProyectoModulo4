package modulo4.proyecto.bean;

import modulo4.proyecto.service.SessionService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modulo4.proyecto.dao.BookDAO;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.model.Office;
import modulo4.proyecto.service.OfficeService;

@ManagedBean
@SessionScoped
public class MyCarBean implements Serializable
{
    @ManagedProperty("#{serviceOffice}")
    private OfficeService serviceOffice;
    private ArrayList<Office> listOffices;
    private ArrayList<Book> listBooks;
    private Office currentOffice;
    private Float total;
    private Integer row;
    
    public MyCarBean () {    
    }
    
    @PostConstruct
    public void init()
    {
        cleanVariables();
        this.listBooks = new ArrayList<Book>();
        this.listOffices = serviceOffice.getListOffice();
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
            RequisitionDAO requisitionDAO = new RequisitionDAO();
            if(requisitionDAO.insert(total, idSession, currentOffice.getId(), listBooks) != 0)
            {
                listBooks.clear();
                cleanVariables();
                return "/public/MyRequisitions.jsf?faces-redirect=true";
            }
        }
        return "/public/Login.jsf?faces-redirect=true";
    }
          

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public ArrayList<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<Book> listBooks) {
        this.listBooks = listBooks;
    }

    public ArrayList<Office> getListOffices() {
        return listOffices;
    }

    public void setListOffices(ArrayList<Office> listOffices) {
        this.listOffices = listOffices;
    }

    public OfficeService getServiceOffice() {
        return serviceOffice;
    }

    public void setServiceOffice(OfficeService serviceOffice) {
        this.serviceOffice = serviceOffice;
    }

    public Office getCurrentOffice() {
        return currentOffice;
    }

    public void setCurrentOffice(Office currentOffice) {
        this.currentOffice = currentOffice;
    }
}
