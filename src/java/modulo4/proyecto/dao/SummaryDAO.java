package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.Summary;

public class SummaryDAO 
{
    private ConnectionDB currentConnection;
    
    public Summary findById (int id) 
    {
        String query = "SELECT * FROM summary WHERE id = ? ";
        
        try 
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rst = pstm.executeQuery();
            if(rst.next())
            {
                return new Summary(rst.getInt("id"), 
                        rst.getString("type"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }    
    
}
