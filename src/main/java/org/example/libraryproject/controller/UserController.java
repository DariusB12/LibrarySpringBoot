package org.example.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.userDTO.UserRequest;
import org.example.libraryproject.controller.service.UserService;
import org.example.libraryproject.exception.exceptions.UserServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regular")
public class UserController {
    private final UserService userService;

    /***
     * Delete a user account if the password is correct and if the user exists
     */
    @PostMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(@RequestBody UserRequest request) throws UserServiceException {
        return ResponseEntity.ok(userService.deleteAccount(request));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(userService.getAllBooks());
    }

}
