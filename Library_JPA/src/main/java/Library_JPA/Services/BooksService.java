package Library_JPA.Services;

import Library_JPA.Models.Book;
import Library_JPA.Models.Person;
import Library_JPA.Repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("yearOfRelease"));
        else
            return booksRepository.findAll();
    }
    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfRelease"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }
    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        updatedBook.setBookId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        booksRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
    public Person getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);

    }
    @Transactional
    // Освобождает книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
                });
    }
    @Transactional
    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    book.setTakenAt(new Date()); // текущее время
                }
        );
    }
}
