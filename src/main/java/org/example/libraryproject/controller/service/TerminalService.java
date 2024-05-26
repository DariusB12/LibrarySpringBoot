package org.example.libraryproject.controller.service;


import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.regularDTO.RegularResponse;
import org.example.libraryproject.controller.dto.regularDTO.TerminalDTO;
import org.example.libraryproject.controller.utils.JsonUtilsConverter;
import org.example.libraryproject.model.Terminal;
import org.example.libraryproject.repository.TerminalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminalService {
    private final TerminalRepository terminalRepository;
    private final JsonUtilsConverter jsonUtilsConverter;

    /***
     * Return a regular response with all the terminals(TerminalDTO)
      * @return RegularResponse
     */
    public RegularResponse getAllTerminals(){
        List<Terminal> allTerminals =terminalRepository.findAll();
        List<TerminalDTO> allTerminalsDTO = new ArrayList<>();
        for(Terminal terminal : allTerminals){
            allTerminalsDTO.add(jsonUtilsConverter.terminalToTerminalDTO(terminal));
        }
        return RegularResponse.builder()
                .terminals(allTerminalsDTO)
                .build();
    }
}
