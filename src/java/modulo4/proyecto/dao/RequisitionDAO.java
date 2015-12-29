package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.Book;
import modulo4.proyecto.model.Office;
import modulo4.proyecto.model.Requisition;
import modulo4.proyecto.model.Summary;

public class RequisitionDAO 
{
    private ConnectionDB currentConnection;
    
    public int insert(float total, int user_id, int office_id, ArrayList<Book> listBooks) 
    {
        int result = 0;
        String query = "INSERT INTO requisition (total, date, summary_id, "
                    + " user_id, office_id, status) VALUES (?,NOW(),1,?,?,1) ";
        
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setFloat(1, total);
            pstm.setInt(2, user_id);
            pstm.setInt(3, office_id);
            
            if(pstm.executeUpdate() != 0)
            {
                ResultSet rst = pstm.getGeneratedKeys();
                
                if(rst.next())
                {
                    BookHasRequisitonDAO bookHasRequisitionDAO = new BookHasRequisitonDAO();
                    
                    for(int i = 0; i < listBooks.size(); i++)
                    {
                        if(bookHasRequisitionDAO.insert(listBooks.get(i).getId(), rst.getInt(1)) != 0)
                        {
                            result += 1;
                        }
                        else
                        {
                            throw new SQLException("Error to insert bookHasRequisition");
                        }
                    }   
                }
            }
            
            pstm.close();
            currentConnection.closeConecction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Requisition> findByIdUser (int idUser) 
    {
        ArrayList<Requisition> listRequisitions = new ArrayList<Requisition>();
        
        String query = "SELECT r.id, "
                + " r.total, "
                + " r.date, "
                + " r.user_id, "
                + " s.type AS summary_type, "
                + " o.name AS office_name "
                + " FROM requisition AS r "
                + " INNER JOIN summary AS s "
                + " ON r.summary_id = s.id "
                + " INNER JOIN office AS o "
                + " ON r.office_id = o.id "
                + " WHERE r.user_id = ? "
                + " AND r.status = 1 ";
        
        try 
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, idUser);
            ResultSet rst = pstm.executeQuery();
            
            while(rst.next())
            {
                Summary summary = new Summary();
                summary.setType(rst.getString("summary_type"));
                
                Office office = new Office();
                office.setName(rst.getString("office_name"));
                
                Requisition requisition = new Requisition();
                requisition.setId(rst.getInt("id"));
                requisition.setTotal(rst.getFloat("total"));
                requisition.setDate(rst.getDate("date"));
                requisition.setTime(rst.getTime("date"));
                requisition.setSummary(summary);
                requisition.setOffice(office);
                
                listRequisitions.add(requisition);
            }
            rst.close();
            currentConnection.closeConecction();
            return listRequisitions;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete (int id)
    {
        boolean result = false;
        String query = "UPDATE requisition SET status = 0 WHERE id = ?";
     
        try
        {
            currentConnection = new ConnectionDB();    
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, id);
            
            if(pstm.executeUpdate() != 0)
            {
                result = true;
            }
            
            pstm.close();
            currentConnection.closeConecction();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public Requisition findById (int requisition_id, int user_id) 
    {   
        Requisition requisition = new Requisition();
        
        String query = "SELECT r.id, "
                + " r.total, "
                + " r.date, "
                + " r.user_id, "
                + " s.type AS summary_type, "
                + " o.name AS office_name "
                + " FROM requisition AS r "
                + " INNER JOIN summary AS s "
                + " ON r.summary_id = s.id "
                + " INNER JOIN office AS o "
                + " ON r.office_id = o.id "
                + " WHERE r.id = ? "
                + " AND r.user_id = ? "
                + " AND r.status = 1 ";
        
        try 
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, requisition_id);
            pstm.setInt(2, user_id);
            ResultSet rst = pstm.executeQuery();
            
            if(rst.next())
            {
                Summary summary = new Summary();
                summary.setType(rst.getString("summary_type"));
                
                Office office = new Office();
                office.setName(rst.getString("office_name"));
                
                requisition.setId(rst.getInt("id"));
                requisition.setTotal(rst.getFloat("total"));
                requisition.setDate(rst.getDate("date"));
                requisition.setTime(rst.getTime("date"));
                requisition.setSummary(summary);
                requisition.setOffice(office);
            }
            rst.close();
            currentConnection.closeConecction();
            return requisition;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
