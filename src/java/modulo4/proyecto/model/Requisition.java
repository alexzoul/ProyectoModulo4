package modulo4.proyecto.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Requisition {
    private Integer id;
    private Float total;
    private Date date;
    private Time time;
    private Summary summary;
    private User user;
    private Office office;
    private Integer status;
    private ArrayList<Book> books;
    
    public Requisition () {
    }
    
    public Requisition (Integer id, Float total, Date date, Summary summary, User user,
                        Office office, Integer status) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.summary = summary;
        this.user = user;
        this.office = office;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
