package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.User;

public class UserDAO 
{
    private ConnectionDB currentConnection;
    
    public User validateUser (String email, String password)
    {
        String query = "SELECT * FROM user "
                    + " WHERE email = ? AND password = ? "
                    + " AND status = 1";
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rst = pstm.executeQuery();
            
            if(rst.next())
            {
                User user = new User();
                
                user.setId(rst.getInt("id"));
                user.setName(rst.getString("name"));
                user.setPaternal_name(rst.getString("paternal_name"));
                user.setMaternal_name(rst.getString("maternal_name"));
                user.setEmail(rst.getString("email"));
                
                return user;
            }
            pstm.close();
            currentConnection.closeConecction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }    
}
