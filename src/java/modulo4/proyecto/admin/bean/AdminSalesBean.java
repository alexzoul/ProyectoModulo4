package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Requisition;
import modulo4.proyecto.service.SessionService;

@ManagedBean
@RequestScoped
public class AdminSalesBean implements Serializable
{
    private ArrayList<Requisition> listRequisition;
    private Integer row;
    
    public AdminSalesBean() 
    { 
        SessionService sessionService = new SessionService();
        sessionService.checkSessionAdmin();
    }

    @PostConstruct
    public void init() 
    {        
        RequisitionDAO requisitionDAO = new RequisitionDAO();
        listRequisition = requisitionDAO.findAll();
        row = 0;
    }
    
    public String delete(int id)
    {
        RequisitionDAO requisitionDAO = new RequisitionDAO();
        if(requisitionDAO.delete(id))
        {
            return "/private/Sales.jsf?faces-redirect=true";
        }
        //Falta agregar pagina de error
        return "Error";
    }
    
    public ArrayList<Requisition> getListRequisition() {
        return listRequisition;
    }

    public void setListRequisition(ArrayList<Requisition> listRequisition) {
        this.listRequisition = listRequisition;
    }

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
