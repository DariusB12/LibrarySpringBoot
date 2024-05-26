package org.example.libraryproject.controller.dto.regularDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.model.TerminalType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerminalDTO {
    private Integer id;
    private String name;
    private String location;
    @JsonProperty("terminal_type")
    private TerminalType terminalType;
}
