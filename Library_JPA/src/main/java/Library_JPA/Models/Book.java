package Library_JPA.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @NotEmpty(message="Название книги не может быть пустым")
    @Column(name="title")
    @Size(min=3,max=50,message = "Название книги должно быть от 3 до 50 символов")
    private String title;
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Column(name="author")
    @Size(min=3,max=50,message = "Имя автора должно быть от 3 до 50 символов")
    private String author;
    @Min(value=1500,message = "Год выпуска не может быть меньше 1500")
    @Column(name="year_of_release")
    private int yearOfRelease;
    @Column(name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;
    @Transient
    private boolean expired;

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "person_id")
    private Person owner;
    public Book(String title, String author, int yearOfRelease){
        this.author = author;
        this.yearOfRelease = yearOfRelease;
        this.title = title;
    }
    public Book(){

    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
