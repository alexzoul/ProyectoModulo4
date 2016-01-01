package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.Role;
import modulo4.proyecto.model.User;

public class UserDAO 
{
    private ConnectionDB currentConnection;
    
    public User checkUser (String email, String password, String role_type)
    {
        String query = "SELECT u.id, "
                + " u.email, "
                + " u.password, "
                + " u.name, "
                + " u.paternal_name, "
                + " u.maternal_name "
                + " FROM user AS u "
                + " INNER JOIN role AS r "
                + " ON r.id = u.role_id "
                + " WHERE u.email = ? "
                + " AND u.password = ? "
                + " AND r.type = ? "
                + " AND u.status = 1 ";
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, password);
            pstm.setString(3, role_type);
            ResultSet rst = pstm.executeQuery();
            
            if(rst.next())
            {
                User user = new User();
                Role role = new Role();
                
                user.setId(rst.getInt("id"));
                user.setName(rst.getString("name"));
                user.setPaternal_name(rst.getString("paternal_name"));
                user.setMaternal_name(rst.getString("maternal_name"));
                user.setEmail(rst.getString("email"));
                
                role.setType(role_type);
                user.setRole(role);
                
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
                        + " email, phone_number, password, role_id, status, register_date) "
                                        + " VALUES (?,?,?,?,?,?,2,1,NOW()) ";
        
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPaternal_name());
            pstm.setString(3, user.getMaternal_name());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getPhone_number());
            pstm.setString(6, user.getPassword());
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
        String query = "SELECT * FROM user WHERE id = ? ";
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
                user.setPhone_number(rst.getString("phone_number"));
                user.setRegister_time(rst.getTime("register_date"));
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
    
    public User validateAdmin (String email, String password)
    {
        String query = "SELECT u.id, "
                + " u.email, "
                + " u.password, "
                + " u.name, "
                + " u.paternal_name, "
                + " u.maternal_name "
                + " FROM user AS u "
                + " INNER JOIN role AS r "
                + " ON r.id = u.role_id "
                + " WHERE u.email = ? "
                + " AND u.password = ? "
                + " AND r.type = ? "
                + " AND u.status = 1 ";
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, password);
            pstm.setString(3, "Administrador");
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
    
    public ArrayList<User> findAll () {
        ArrayList<User> users = new ArrayList<User> ();
        String query = "SELECT u.* "
                    + " FROM user AS u "
                    + " INNER JOIN role AS r "
                    + " ON r.id = u.role_id "
                    + " WHERE r.type = ? "
                    + " AND u.status = 1 "
                    + " ORDER BY u.name, "
                    + " u.paternal_name, "
                    + " u.maternal_name ASC ";
        
        try 
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, "Cliente");
            ResultSet rst = pstm.executeQuery();
            while(rst.next())
            {
                User user = new User();
                user.setId(rst.getInt("id"));
                user.setName(rst.getString("name"));
                user.setPaternal_name(rst.getString("paternal_name"));
                user.setMaternal_name(rst.getString("maternal_name"));
                user.setEmail(rst.getString("email"));
                user.setRegister_date(rst.getDate("register_date"));
                user.setRegister_time(rst.getTime("register_date"));
                users.add(user);
            }
            pstm.close();
            currentConnection.closeConecction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public boolean delete (int id)
    {
        boolean result = false;
        String query = "UPDATE user SET status = 0 WHERE id = ?";
     
        try
        {
            currentConnection = new ConnectionDB();    
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, id);
            
            if(pstm.executeUpdate() == 1)
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
    
    public boolean checkEmail (String email)
    {
        boolean result = false;
        String query = "SELECT email FROM user WHERE email = ? ";
     
        try
        {
            currentConnection = new ConnectionDB();    
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, email);
            ResultSet rst = pstm.executeQuery();
            
            if(rst.next())
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
}
