package org.example.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private Integer booksAvailable;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private Integer pages;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
}
