package modulo4.proyecto.model;

public class Office 
{
    private Integer id;
    private String name;
    private String street;
    private String int_number;
    private String ext_number;
    private String neighborhood;
    private String city;
    private String state;
    private String zip_code;
    
    public Office() {
    }
    
    public Office(Integer id, String name, String street, String int_number,
                  String ext_number, String neighborhood, String city,
                  String state, String zip_code) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.int_number = int_number;
        this.ext_number = ext_number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getInt_number() {
        return int_number;
    }

    public void setInt_number(String int_number) {
        this.int_number = int_number;
    }

    public String getExt_number() {
        return ext_number;
    }

    public void setExt_number(String ext_number) {
        this.ext_number = ext_number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }        
}
