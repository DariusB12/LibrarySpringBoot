package org.example.libraryproject.repository;

import org.example.libraryproject.model.Book;
import org.example.libraryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT b.title, b.author, b.publisher, b.publishDate, b.pages, b.description, b.imagePath, COUNT(b) AS bookCount " +
            "FROM Book b " +
            "WHERE b.user IS NULL " +
            "GROUP BY b.title, b.author, b.publisher, b.publishDate, b.pages, b.description, b.imagePath ")
    List<Object[]> findDistinctBooksWithCount();

    @Query(value = "SELECT * FROM Book b " +
            "WHERE b.title = :title " +
            "AND b.author = :author " +
            "AND b.publisher = :publisher " +
            "AND b.publish_date = :publishDate " +
            "AND b.pages = :pages " +
            "AND b.description = :description " +
            "AND b.image_path = :imagePath " +
            "AND b.user_id IS NULL " +
            "LIMIT 1 ",
            nativeQuery = true)
    Optional<Book> findFirstAvailableBook(String title, String author, String publisher, LocalDate publishDate, Integer pages, String description, String imagePath);
    @Query("SELECT DISTINCT b FROM Book b " +
            "INNER JOIN b.appointments a " +
            "INNER JOIN a.user u ON u.id = b.user.id " +
            "WHERE a.confirmed = true AND u.username = :username")
    List<Book> findUsersBooksConfirmedBorrowed(String username);

    @Query("SELECT b FROM Book b " +
            "WHERE b.user.username = :username")
    List<Book> findBooksReferredUser(String username);
}
