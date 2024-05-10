package org.example.libraryproject.repository;

import org.example.libraryproject.model.Book;
import org.example.libraryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT b.title, b.author, b.publisher, b.publishDate, b.pages, b.description, b.imagePath, COUNT(b) AS bookCount " +
            "FROM Book b " +
            "GROUP BY b.title, b.author, b.publisher, b.publishDate, b.pages, b.description, b.imagePath")
    List<Object[]> findDistinctBooksWithCount();
}
