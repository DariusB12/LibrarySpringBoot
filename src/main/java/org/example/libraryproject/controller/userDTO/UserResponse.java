package org.example.libraryproject.controller.userDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.model.Book;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("books")
    private List<BookDTO> books;
}
