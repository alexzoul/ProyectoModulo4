package modulo4.proyecto.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import modulo4.proyecto.database.ConnectionDB;

public class TestDAO 
{
    public void consulta() throws Exception
    {
        ConnectionDB currentConnection = new ConnectionDB();
        String query = "SELECT * FROM datos";
        Statement stm = currentConnection.getConnection().createStatement();
        ResultSet data = stm.executeQuery(query);
        
        if(!data.next())
        {
            System.out.println("Empty");
        }
        else
        {
            do
            {
                System.out.println(data.getString(1));
            }
            while(data.next());
        }
        data.close();
        stm.close();
        currentConnection.cerrarConexion();
    }

}
