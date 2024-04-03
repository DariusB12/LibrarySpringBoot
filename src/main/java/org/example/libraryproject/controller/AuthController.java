package org.example.libraryproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.SignInRequest;
import org.example.libraryproject.controller.dto.SignUpRequest;
import org.example.libraryproject.exception.AuthServiceException;
import org.example.libraryproject.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) throws AuthServiceException {
        return ResponseEntity.ok(authService.signIn(request));
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) throws ValidationException {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() throws Exception {
        System.out.println("entered in the function which requires jwt authentication");
        return ResponseEntity.ok("entered in tet function which requires jwt authentication");
    }


}
