package modulo4.proyecto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modulo4.proyecto.database.ConnectionDB;
import modulo4.proyecto.model.Book;

public class BookHasRequisitonDAO 
{
    private ConnectionDB currentConnection;
    
    public int insert (int id_book, int id_requisition)
    {
        int result = 0;
        String query = "INSERT INTO book_has_requisition (date, book_id, requisition_id) "
                        + " VALUES (NOW(),?,?) ";
        
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
    
    public ArrayList<Book> findByIdRequisition(int idRequisition)
    {
        ArrayList<Book> listBooks = new ArrayList<Book>();
        
        String query = "SELECT  b.id, "
                + " b.title, "
                + " b.author, "
                + " b.editorial, "
                + " b.year, "
                + " b.image, "
                + " b.pages, "
                + " b.price "
                + " FROM book_has_requisition AS bhr "
                + " INNER JOIN book AS b "
                + " ON b.id = bhr.book_id "
                + " WHERE bhr.requisition_id = ? ";
        try
        {
            currentConnection = new ConnectionDB();
            PreparedStatement pstm = currentConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, idRequisition);
            ResultSet rst = pstm.executeQuery();
            
            while(rst.next())
            {
                Book book = new Book();
                book.setId(rst.getInt("id"));
                book.setTitle(rst.getString("title"));
                book.setAuthor(rst.getString("author"));
                book.setEditorial(rst.getString("editorial"));
                book.setYear(rst.getInt("year"));
                book.setImage(rst.getString("image"));
                book.setPages(rst.getInt("pages"));
                book.setPrice(rst.getFloat("price"));
                listBooks.add(book);
            }
            rst.close();
            currentConnection.closeConecction();
            return listBooks;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
