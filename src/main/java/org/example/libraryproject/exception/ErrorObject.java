package org.example.libraryproject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorObject{
    private Integer statusCode;
    private String message;
    private LocalDateTime dateTime;
}
