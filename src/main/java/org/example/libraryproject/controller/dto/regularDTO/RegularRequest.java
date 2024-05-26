package org.example.libraryproject.controller.dto.regularDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.model.AppointmentType;
import org.example.libraryproject.model.Book;
import org.example.libraryproject.model.Terminal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegularRequest {
    @JsonProperty("password")
    private String password;
    @JsonProperty("type_appointment")
    private AppointmentType typeAppointment;
    @JsonProperty("terminal")
    private TerminalDTO terminal;
    @JsonProperty("description")
    private String description;
    @JsonProperty("book")
    private BookDTO book;
    @JsonProperty("appointment")
    private AppointmentDTO appointment;
}
