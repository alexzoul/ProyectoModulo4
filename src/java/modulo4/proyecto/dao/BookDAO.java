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
    
    public ArrayList<Book> findAll () 
    {
        ArrayList<Book> books = new ArrayList<Book> ();
        String query = "SELECT * FROM book ORDER BY title, author, editorial DESC";
        
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
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {  
            try
            {
                if(currentConnection != null)
                {
                    currentConnection.closeConecction();
                }
            } catch(Exception e) {}
        }
        return books;
    }
    
    public ArrayList<Book> findText (String findText) 
    {
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
        finally 
        {  
            try
            {
                if(currentConnection != null)
                {
                    currentConnection.closeConecction();
                }
            } catch(Exception e) {}
        }
        return books;
    }
    
    public Book findById (int id) 
    {
        String query = "SELECT * FROM book WHERE id = ? ";
        Book book = null;
        
        try 
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rst = pstm.executeQuery();
            
            if(rst.next())
            {
                book = new Book(rst.getInt("id"), 
                            rst.getString("title"), 
                            rst.getString("author"), 
                            rst.getString("editorial"), 
                            rst.getInt("year"),
                            rst.getString("description"), 
                            rst.getString("image"), 
                            rst.getInt("pages"),
                            rst.getFloat("price"));
            }
            
            rst.close();
            currentConnection.closeConecction();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {  
            try
            {
                if(currentConnection != null)
                {
                    currentConnection.closeConecction();
                }
            } catch(Exception e) {}
        }
        return book;
    }
    
    public boolean updateById (Book book)
    {
        boolean result = false;
        String query = "UPDATE book "
                        + " SET title = ?, "
                        + " author = ?, "
                        + " editorial = ?, "
                        + " year = ?, "
                        + " description = ?, "
                        + " pages = ?, "
                        + " price = ? "
                        + " WHERE id = ? ";
     
        try
        {
            currentConnection = new ConnectionDB();    
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setString(1, book.getTitle());
            pstm.setString(2, book.getAuthor());
            pstm.setString(3, book.getEditorial());
            pstm.setInt(4, book.getYear());
            pstm.setString(5, book.getDescription());
            pstm.setInt(6, book.getPages());
            pstm.setFloat(7, book.getPrice());
            pstm.setInt(8, book.getId());
            
            if(pstm.executeUpdate() == 1)
            {
                result = true;
            }
            
            pstm.close();
            currentConnection.closeConecction();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {  
            try
            {
                if(currentConnection != null)
                {
                    currentConnection.closeConecction();
                }
            } catch(Exception e) {}
        }
        return result;
    }
}
