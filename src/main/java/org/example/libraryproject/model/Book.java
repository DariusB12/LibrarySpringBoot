package org.example.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private Integer pages;
    private String description;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
    @OneToMany(mappedBy = "book")
    private List<Appointment> appointments;
}
