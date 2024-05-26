package org.example.libraryproject.controller.dto.regularDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.model.Appointment;
import org.example.libraryproject.model.Terminal;

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
    @JsonProperty("terminals")
    private List<TerminalDTO> terminals;
    @JsonProperty("appointments")
    private List<AppointmentDTO> appointments;
}
