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
    
    public int insert(User user) 
    {
        int result = 0;
        String query = "INSERT INTO user (name, paternal_name, maternal_name, "
                        + " email, password, role_id, status, register_date) "
                                        + " VALUES (?,?,?,?,?,2,1,NOW());";
        
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPaternal_name());
            pstm.setString(3, user.getMaternal_name());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getPassword());
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
    
    public User findById (int id)
    {
        String query = "SELECT * FROM user WHERE id = ?";
        User user = null;
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rst = pstm.executeQuery();
            
            if(rst.next())
            {
                user = new User();
                user.setName(rst.getString("name"));
                user.setPaternal_name(rst.getString("paternal_name"));
                user.setMaternal_name(rst.getString("maternal_name"));
                user.setEmail(rst.getString("email"));
                user.setRegister_date(rst.getDate("register_date"));
            }
            pstm.close();
            currentConnection.closeConecction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
