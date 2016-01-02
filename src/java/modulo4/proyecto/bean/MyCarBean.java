package modulo4.proyecto.bean;

import modulo4.proyecto.session.SessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.model.Office;
import modulo4.proyecto.service.BookService;
import modulo4.proyecto.service.OfficeService;

@ManagedBean
@RequestScoped
public class MyCarBean implements Serializable
{
    @ManagedProperty("#{serviceOffice}")
    private OfficeService serviceOffice;
    
    @ManagedProperty("#{serviceBook}")
    private BookService serviceBook;
    
    private ArrayList<Office> listOffices;
    private ArrayList<Book> listBooks;
    private Office currentOffice;
    private Float total;
    private Integer row;
    
    public MyCarBean () 
    {  
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Cliente");
    }
    
    @PostConstruct
    public void init()
    {
        row = 0;
        listBooks = serviceBook.getListBooks();
        listOffices = serviceOffice.getListOffice(); 
        total = serviceBook.getTotal();
    }
     
    public void removeBook (Book book)
    {
        serviceBook.removeBook(book);
        total = serviceBook.getTotal();
    }
     
    public String buyBooks()
    {
        if(listBooks.size() > 0)
        {
            SessionBean sessionService = new SessionBean();
            
            int id = sessionService.getIdSession("Cliente");
            
            RequisitionDAO requisitionDAO = new RequisitionDAO();
            if(requisitionDAO.insert(total, id, currentOffice.getId(), listBooks) != 0)
            {
                serviceBook.setListBooks(new ArrayList<Book>());
                serviceBook.setTotal(0.0f);
                return "/public/MyRequisitions.jsf?faces-redirect=true";
            }
            return "Error";
        }
        else
        {
            return null;
        }
    }

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
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

    public BookService getServiceBook() {
        return serviceBook;
    }

    public void setServiceBook(BookService serviceBook) {
        this.serviceBook = serviceBook;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
