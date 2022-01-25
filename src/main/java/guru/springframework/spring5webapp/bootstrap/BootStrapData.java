package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Rod", "Johnson");
        Book ddd = new Book("Domain Driven Design", "3287293472843", "Publisher XY");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author dave = new Author("Dave", "Johnson");
        Book noEJB = new Book("J2EE Development", ",09423874283974", "Publisher ZZ");

        dave.getBooks().add(noEJB);
        noEJB.getAuthors().add(dave);

        authorRepository.save(dave);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println(String.format("Number of Books: %s", bookRepository.count()));
    }
}
