package modulo4.proyecto.model;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String editorial;
    private Integer year;
    private String description;
    private String image;
    private Integer pages;
    private Float price;
    
    public Book () {
    }
    
    public Book (Integer id, String title, String author, String editorial, Integer year, 
                 String description, String image, Integer pages, Float price) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
