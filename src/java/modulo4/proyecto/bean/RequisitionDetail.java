package modulo4.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import modulo4.proyecto.dao.BookHasRequisitonDAO;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.model.Requisition;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@ViewScoped
public class RequisitionDetail implements Serializable
{   
    private String id;
    private Integer row;
    private Requisition currentRequisition;
    private ArrayList<Book> listBooks;
    
    public RequisitionDetail () 
    {
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Cliente");
    }  
    
    @PostConstruct
    public void init ()
    {   
        SessionBean sessionService = new SessionBean();
        int idUser = sessionService.getIdSession("Cliente");
        row = 0;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        id = (String) facesContext.getExternalContext().getRequestParameterMap().get("id");
        
        if(id != null)
        {
            RequisitionDAO requisitionDAO = new RequisitionDAO();
            currentRequisition = requisitionDAO.findById(Integer.parseInt(id), idUser);

            if(currentRequisition != null)
            {
                BookHasRequisitonDAO bookHasRequisitonDAO = new BookHasRequisitonDAO();
                listBooks = bookHasRequisitonDAO.findByIdRequisition(currentRequisition.getId());
            }
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
    
    public Requisition getCurrentRequisition() {
        return currentRequisition;
    }

    public void setCurrentRequisition(Requisition currentRequisition) {
        this.currentRequisition = currentRequisition;
    }

    public ArrayList<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<Book> listBooks) {
        this.listBooks = listBooks;
    }    
}
