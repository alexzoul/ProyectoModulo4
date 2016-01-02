package modulo4.proyecto.admin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Requisition;
import modulo4.proyecto.session.SessionBean;

@ManagedBean
@RequestScoped
public class AdminSalesBean implements Serializable
{
    private ArrayList<Requisition> listRequisition;
    private Integer row;
    
    public AdminSalesBean() 
    { 
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Administrador");
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
        return null;
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
