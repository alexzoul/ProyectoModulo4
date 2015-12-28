package modulo4.proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.OfficeDAO;
import modulo4.proyecto.model.Office;

@ManagedBean
@RequestScoped
public class OfficeBean implements Serializable
{
    private ArrayList<Office> listOffice;
    private Integer row;
    
    public OfficeBean() {
    }

    @PostConstruct
    public void init()
    {
        row = 0;
        OfficeDAO officeDAO = new OfficeDAO();
        listOffice = officeDAO.findAll();
    }
    
    public ArrayList<Office> getListOffice() {
        return listOffice;
    }

    public void setListOffice(ArrayList<Office> listOffice) {
        this.listOffice = listOffice;
    }

    public Integer getRow() {
        ++this.row;
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
