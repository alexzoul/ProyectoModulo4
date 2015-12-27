package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import modulo4.proyecto.database.ConnectionDB;

public class BookHasRequisitonDAO 
{
    private ConnectionDB currentConnection;
    
    public int insert (int  id_book, int id_requisition)
    {
        int result = 0;
        String query = "INSERT INTO book_has_requisition (book_id, requisition_id) "
                        + " VALUES (?,?) ";
        
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, id_book);
            pstm.setInt(2, id_requisition);
            result = pstm.executeUpdate();
            pstm.close();
            currentConnection.closeConecction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
