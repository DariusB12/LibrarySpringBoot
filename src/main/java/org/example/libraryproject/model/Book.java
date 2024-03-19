package org.example.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private Integer noOfPieces;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private Integer pages;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
