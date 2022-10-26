package Library_JPA.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name="full_name")
    @NotEmpty(message="ФИО не может быть пустым")
    @Size(min=3,max=50,message="ФИО может быть размером от 3 до 50 символов")
    private String fullName;
    @Column(name="year_of_birth")
    @Min(value=1900,message="Год рождения не может быть меньше 1900")
    private int yearOfBirth;
    @OneToMany(mappedBy = "owner")
    private List<Book> books;
    public Person(String fullName, int yearOfBirth){
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
    public Person(){}

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
