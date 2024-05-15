package org.example.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.service.RegularService;
import org.example.libraryproject.exception.exceptions.UserServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regular")
public class RegularController {
    private final RegularService regularService;

    /***
     * Delete a user account if the password is correct and if the user exists
     */
    @PostMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(@RequestBody RegularRequest request) throws UserServiceException {
        return ResponseEntity.ok(regularService.deleteAccount(request));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(regularService.getAllBooks());
    }

}
