package org.example.libraryproject.controller.dto.regularDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    @JsonProperty("publish_date")
    private LocalDate publishDate;
    private Integer pages;
    private String description;
    @JsonProperty("image_path")
    private String imagePath;
    @JsonProperty("books_available")
    private Integer booksAvailable;
}
