package org.example.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.service.BookService;
import org.example.libraryproject.controller.service.RegularService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    /**
     * retrieve all the books from DB
     * @return RegularResponse
     */
    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * retrieves from DB all the books that a given user has borrowed (borrow appointments confirmed)
     * @return RegularRequest
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getAllBooksUser(@PathVariable("username") String username){
        return ResponseEntity.ok(bookService.getAllConfirmedBorrowBooksUser(username));
    }

}
