package modulo4.proyecto.service;


import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import modulo4.proyecto.dao.OfficeDAO;
import modulo4.proyecto.model.Office;

@ManagedBean(name = "serviceOffice", eager = true)
@ApplicationScoped
public class OfficeService implements Serializable
{
    private ArrayList<Office> listOffice;
    
    public OfficeService() {
    }
    
    @PostConstruct
    public void init()
    {
        OfficeDAO officeDAO = new OfficeDAO();
        listOffice = officeDAO.findAll();
    }
    
    public ArrayList <Office> getListOffice ()
    {
        return listOffice;
    }
}
