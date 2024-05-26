package org.example.libraryproject.controller.dto.employeeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.controller.dto.regularDTO.AppointmentDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @JsonProperty("appointment")
    private AppointmentDTO appointment;
}
