package modulo4.proyecto.bean;

import modulo4.proyecto.session.SessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Requisition;

@ManagedBean
@ViewScoped
public class MyRequisitionsBean implements Serializable
{
    private ArrayList<Requisition> listRequisition;
    private Integer row;
    
    public MyRequisitionsBean () 
    {
        SessionBean sessionService = new SessionBean();
        sessionService.checkSession("Cliente");
    }

    @PostConstruct
    public void init ()
    {
        SessionBean sessionBean = new SessionBean();
        int id = sessionBean.getIdSession("Cliente");
        RequisitionDAO requisitionDAO = new RequisitionDAO();
        listRequisition = requisitionDAO.findByIdUser(id);
        row = 0;
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
    
    public String delete(int id)
    {
        RequisitionDAO requisitionDAO = new RequisitionDAO();
        if(requisitionDAO.delete(id))
        {
            return "/public/MyRequisitions.jsf?faces-redirect=true";
        }
        return "Error";
    }
}
