package org.example.libraryproject.controller.userDTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private Integer pages;
    private String description;
    private String imagePath;
    private Integer booksAvailable;
}
