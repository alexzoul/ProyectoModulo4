package modulo4.proyecto.model;

import java.sql.Date;
import java.sql.Time;

public class Requisition {
    private int id;
    private float total;
    private Date date;
    private Time time;
    private Summary summary;
    private User user;
    private Office office;
    private int status;
    
    public Requisition () {
    }
    
    public Requisition (int id, float total, Date date, Summary summary, User user,
                        Office office, int status) {
        this.id = id;
        this.total = total;
        this.date = date;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
