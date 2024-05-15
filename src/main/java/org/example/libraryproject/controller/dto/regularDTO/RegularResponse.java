package org.example.libraryproject.controller.dto.regularDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegularResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("books")
    private List<BookDTO> books;
}
