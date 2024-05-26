package org.example.libraryproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.authenticationDTO.SignInRequest;
import org.example.libraryproject.controller.dto.authenticationDTO.SignUpRequest;
import org.example.libraryproject.controller.service.AuthService;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.InternalServerException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * logs in a user
     * @param request SignInRequest
     * @return AuthenticationResponse
     * @throws AuthServiceException if the credentials are invalid
     */
    @PostMapping("signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) throws AuthServiceException {
        return ResponseEntity.ok(authService.signIn(request));
    }

    /**
     * register a new user
     * @param request SignUpRequest
     * @return AuthenticationResponse
     * @throws ValidationException if the credentials are invalid
     * @throws AuthServiceException if email,cnp,or username are already used
     * @throws InternalServerException if failed to read the employee-password from app.properties file
     */
    @PostMapping("signUp")
    public synchronized ResponseEntity<?> signUp(@RequestBody SignUpRequest request) throws ValidationException, AuthServiceException, InternalServerException {
        return ResponseEntity.ok(authService.signUp(request));
    }

}
