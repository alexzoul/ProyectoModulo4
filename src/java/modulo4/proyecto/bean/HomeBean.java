package modulo4.proyecto.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modulo4.proyecto.dao.TestDAO;

@ManagedBean
@RequestScoped
public class HomeBean 
{
    public HomeBean() 
    {
    }
    
    @PostConstruct
    public void iniciar()
    {
        try 
        {
            TestDAO test = new TestDAO();
            test.consulta();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void saluda()
    {
        System.out.println("Hola mundo");    
    }
}
