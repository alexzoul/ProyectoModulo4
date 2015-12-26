package modulo4.proyecto.model;

import java.sql.Date;

public class User {
    private int id;
    private String name;
    private String paternal_name;
    private String maternal_name;
    private String email;
    private String password;
    private Date register_date;
    private Role role;
    private int status;
    
    public User() {        
    }
    
    public User(int id, String name, String paternal_name, String maternal_name,
                String email, String password, Date register_date, Role role, 
                int status) {
        this.id = id;
        this.name = name;
        this.paternal_name = paternal_name;
        this.maternal_name = maternal_name;
        this.email = email;
        this.password = password;
        this.register_date = register_date;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternal_name() {
        return paternal_name;
    }

    public void setPaternal_name(String paternal_name) {
        this.paternal_name = paternal_name;
    }

    public String getMaternal_name() {
        return maternal_name;
    }

    public void setMaternal_name(String maternal_name) {
        this.maternal_name = maternal_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
