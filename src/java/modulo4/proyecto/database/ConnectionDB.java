package modulo4.proyecto.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionDB 
{
    private Connection conecction;
    
    public ConnectionDB() throws Exception
    {
        Context contexto = new InitialContext();
        DataSource resource = (DataSource) contexto.lookup("jdbc/mysqlLocal");
        conecction = resource.getConnection();
    }
    public Connection getConnection()
    {
        return conecction;
    }
    public void cerrarConexion() throws SQLException
    {
        conecction.close();
    }
}
