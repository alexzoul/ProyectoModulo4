package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.Book;

public class BookDAO 
{
    private ConnectionDB currentConnection;
    
    public ArrayList<Book> findAll () {
        ArrayList<Book> books = new ArrayList<Book> ();
        String query = "SELECT * FROM book";
        
        try 
        {
            currentConnection = new ConnectionDB();
            Statement stm = currentConnection.getConnection().createStatement();
            ResultSet rst = stm.executeQuery(query);
            while(rst.next())
            {
                books.add(new Book( rst.getInt("id"), 
                                    rst.getString("title"),
                                    rst.getString("author"),
                                    rst.getString("editorial"),
                                    rst.getInt("year"),
                                    rst.getString("description"),
                                    rst.getString("image"),
                                    rst.getInt("pages"),
                                    rst.getFloat("price")));
            }
            stm.close();
            currentConnection.closeConecction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public ArrayList<Book> findText (String findText) {
        ArrayList<Book> books = new ArrayList<Book>();
        String query = "SELECT * FROM book WHERE "
                     + " title LIKE ? OR author LIKE ? ";
        
        try 
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, "%" + findText + "%");
            pstm.setString(2, "%" + findText + "%");
            ResultSet rst = pstm.executeQuery();
            while(rst.next())
            {
                System.out.println(rst.getInt("id"));
                books.add(new Book( rst.getInt("id"), 
                                    rst.getString("title"),
                                    rst.getString("author"),
                                    rst.getString("editorial"),
                                    rst.getInt("year"),
                                    rst.getString("description"),
                                    rst.getString("image"),
                                    rst.getInt("pages"),
                                    rst.getFloat("price")));
            }
            pstm.close();
            currentConnection.closeConecction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return books;
    }
}
