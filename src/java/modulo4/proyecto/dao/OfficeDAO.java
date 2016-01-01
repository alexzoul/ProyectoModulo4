package modulo4.proyecto.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.Office;

public class OfficeDAO
{
    private ConnectionDB currentConnection;
    
    public ArrayList<Office> findAll () {
        ArrayList<Office> listOffices = new ArrayList<Office> ();
        String query = "SELECT * FROM office ORDER BY name ASC";
        
        try 
        {
            currentConnection = new ConnectionDB();
            Statement stm = currentConnection.getConnection().createStatement();
            ResultSet rst = stm.executeQuery(query);
            while(rst.next())
            {
                listOffices.add(new Office(rst.getInt("id"), 
                                    rst.getString("name"),
                                    rst.getString("street"),
                                    rst.getString("int_number"),
                                    rst.getString("ext_number"),
                                    rst.getString("neighborhood"),
                                    rst.getString("city"),
                                    rst.getString("state"),
                                    rst.getString("zip_code")));
            }
            stm.close();
            currentConnection.closeConecction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOffices;
    }
}
