package org.example.libraryproject.controller.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.config.JwtService;
import org.example.libraryproject.controller.authenticationDTO.AuthenticationResponse;
import org.example.libraryproject.controller.authenticationDTO.SignInRequest;
import org.example.libraryproject.controller.authenticationDTO.SignUpRequest;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.UserRepository;
import org.example.libraryproject.validator.IValidator;
import org.example.libraryproject.validator.UserValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IValidator<User> userValidator = new UserValidator();

    public AuthenticationResponse signIn(SignInRequest request) throws AuthServiceException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new AuthServiceException("username not found"));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse signUp(SignUpRequest request) throws ValidationException {
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .cnp(request.getCnp())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .userRole(request.getRole())
                .build();
        //validate the user before saving it
        userValidator.validate(user);

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
