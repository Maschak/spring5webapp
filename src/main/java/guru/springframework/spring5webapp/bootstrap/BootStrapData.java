package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();

        publisher.setCity("Mainz");
        publisher.setAddress("Mainzer Str. 34");
        publisher.setZip("12345");
        publisher.setState("Rheinland-Pflaz");

        Author eric = new Author("Rod", "Johnson");
        Book ddd = new Book("Domain Driven Design", "3287293472843");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author dave = new Author("Dave", "Johnson");
        Book noEJB = new Book("J2EE Development", ",09423874283974");

        dave.getBooks().add(noEJB);
        noEJB.getAuthors().add(dave);
        publisher.getBooks().add(noEJB);

        authorRepository.save(dave);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println(String.format("Number of Books: %s", bookRepository.count()));
        System.out.println(String.format("Publisher Number of Books: %s", publisher.getBooks().size()));
    }
}
