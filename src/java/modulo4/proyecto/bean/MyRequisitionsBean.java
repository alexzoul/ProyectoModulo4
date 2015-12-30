package modulo4.proyecto.bean;

import modulo4.proyecto.service.SessionService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.RequisitionDAO;
import modulo4.proyecto.model.Requisition;

@ManagedBean
@RequestScoped
public class MyRequisitionsBean implements Serializable
{
    private ArrayList<Requisition> listRequisition;
    private Integer row;
    
    public MyRequisitionsBean () {
    }

    @PostConstruct
    public void init ()
    {
        row = 0;
        
        SessionService sessionBean = new SessionService();
        
        int id = sessionBean.getIdSession();
        
        if(id!=0)
        {
            RequisitionDAO requisitionDAO = new RequisitionDAO();
            listRequisition = requisitionDAO.findByIdUser(id);
        }
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
        SessionService sessionBean = new SessionService();
        
        if (sessionBean.checkSession()) 
        {
            RequisitionDAO requisitionDAO = new RequisitionDAO();
            if(requisitionDAO.delete(id))
            {
                return "/public/MyRequisitions.jsf?faces-redirect=true";
            }
            //Falta agregar pagina de error
            return "Error";
        }
        else 
        {
            return "/public/Login.jsf?faces-redirect=true";
        }
    }
    
    
}
