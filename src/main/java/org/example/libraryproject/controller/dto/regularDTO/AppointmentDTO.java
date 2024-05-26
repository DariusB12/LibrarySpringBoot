package org.example.libraryproject.controller.dto.regularDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.model.AppointmentType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private LocalDateTime issued;
    private String username;
    private AppointmentType type;
    private TerminalDTO terminal;
    private String description;
    private Boolean confirmed;
    private BookDTO book;
}
