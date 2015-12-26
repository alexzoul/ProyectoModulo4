package modulo4.proyecto.model;

import java.sql.Date;

public class Requisition {
    private int id;
    private Date datetime;
    private Summary summary;
    private User user;
    private Office office;
    private int status;
    
    public Requisition () {
    }
    
    public Requisition (int id, Date datetime, Summary summary, User user,
                        Office office, int status) {
        this.id = id;
        this.datetime = datetime;
        this.summary = summary;
        this.user = user;
        this.office = office;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}