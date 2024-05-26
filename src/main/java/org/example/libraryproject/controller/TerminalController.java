package org.example.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.service.TerminalService;
import org.example.libraryproject.repository.TerminalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terminals")
public class TerminalController {
    private final TerminalService terminalService;

    /**
     * retrieve all the terminals from DB
     * @return RegularResponse
     */
    @GetMapping
    public ResponseEntity<?> getAllTerminals(){
        return ResponseEntity.ok(terminalService.getAllTerminals());
    }
}
