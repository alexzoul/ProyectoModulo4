package modulo4.proyecto.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String editorial;
    private int year;
    private String description;
    private String image;
    private int pages;
    private float price;
    
    public Book () {
    }
    
    public Book (int id, String title, String author, String editorial, int year, 
                 String description, String image, int pages, float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.year = year;
        this.description = description;
        this.image = image;
        this.pages = pages;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
