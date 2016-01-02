package modulo4.proyecto.bean;

import modulo4.proyecto.session.SessionBean;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        return null;
    }
    
    public int diffDaysDate(String stringDate, String stringTime) 
    {
        SimpleDateFormat formatText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(stringDate != "")
        {
            try 
            {
                Date date = formatText.parse(stringDate + " " + stringTime);
                long time = date.getTime();
                long currentTime = new Date().getTime();
                long diffTime = currentTime - time;

                int diffDays = (int) Math.floor(diffTime / (1000 * 60 * 60 * 24));

                return diffDays;
            } 
            catch (ParseException ex) 
            {
                ex.printStackTrace();
                return 0;
            }
        }
        return 0;
    }
    
    public String convertTime(String time)
    {
        try
        {
            DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
            DateFormat formatTimeFinal = new SimpleDateFormat("h:mm a");
            return formatTimeFinal.format(formatTime.parse(time)).toLowerCase();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
