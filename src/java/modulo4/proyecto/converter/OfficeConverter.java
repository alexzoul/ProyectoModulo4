package modulo4.proyecto.converter;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modulo4.proyecto.model.Office;
import modulo4.proyecto.service.OfficeService;

@FacesConverter("converterOffice")
public class OfficeConverter implements Converter
{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value != null && value.trim().length() > 0)
        {
            try
            {
                OfficeService service = (OfficeService) 
                        context.getExternalContext()
                               .getApplicationMap()
                               .get("serviceOffice");
                
                ArrayList<Office> listOffices = service.getListOffice();
                for(int i = 0; i < listOffices.size(); i++)
                {
                    if(listOffices.get(i).getId() == Integer.parseInt(value))
                    {
                        return listOffices.get(i);
                    }
                }
            }
            catch(NumberFormatException e)
            {
                throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error de Conversion",
                        "Libro no valido"));
            }
        }
        System.out.println(value);
        return new Office();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value != null)
        {
            return String.valueOf(((Office) value).getId());
        }
        else
        {
            return null;
        }
    }    
}
