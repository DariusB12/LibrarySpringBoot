package org.example.libraryproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.authenticationDTO.SignInRequest;
import org.example.libraryproject.controller.authenticationDTO.SignUpRequest;
import org.example.libraryproject.controller.service.AuthService;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.Book;
import org.example.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
//    @Autowired
//    private BookRepository bookRepository;
    private final AuthService authService;
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) throws AuthServiceException {
        return ResponseEntity.ok(authService.signIn(request));
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) throws ValidationException {
        return ResponseEntity.ok(authService.signUp(request));
    }
//    @PostMapping("/populate")
//    public ResponseEntity<?> populate(){
//        List<Book> books = new ArrayList<>();
//        for(int i= 0;i<10;i++){
//            books.add(Book.builder()
//                    .title("Moara cu noroc")
//                    .author("Ioan Slavici")
//                    .publisher("Editura Compas")
//                    .publishDate(LocalDate.now())
//                    .pages(134)
//                    .description("there was a man and a farm with a lot of unfortune")
//                    .imagePath("")  // Adjust as needed
//                    .build());
//        }
//        for(int i= 0;i<10;i++){
//            books.add(Book.builder()
//                    .title("Moara cu noroc")
//                    .author("Ioan Slavici")
//                    .publisher("MaiBineCitind")
//                    .publishDate(LocalDate.now())
//                    .pages(134)
//                    .description("there was a man and a farm with a lot of unfortune")
//                    .imagePath("")  // Adjust as needed
//                    .build());
//        }
//        for(int i= 0;i<7;i++){
//            books.add(Book.builder()
//                    .title("Casa alba")
//                    .author("Marin Sorescu")
//                    .publisher("Paralela 45")
//                    .publishDate(LocalDate.now())
//                    .pages(150)
//                    .description("there was a father and a family with a lot of unfortune")
//                    .imagePath("")  // Adjust as needed
//                    .build());
//        }
//        bookRepository.saveAll(books);
//        return ResponseEntity.ok("saved books");
//    }

}
