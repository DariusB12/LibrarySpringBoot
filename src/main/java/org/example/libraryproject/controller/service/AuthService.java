package org.example.libraryproject.controller.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.config.JwtService;
import org.example.libraryproject.controller.dto.authenticationDTO.AuthenticationResponse;
import org.example.libraryproject.controller.dto.authenticationDTO.SignInRequest;
import org.example.libraryproject.controller.dto.authenticationDTO.SignUpRequest;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.InternalServerException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.AccountState;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.UserRepository;
import org.example.libraryproject.validator.IValidator;
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
    private final IValidator<SignUpRequest> signUpReqValidator;

    public AuthenticationResponse signIn(SignInRequest request) throws AuthServiceException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthServiceException("username not found"));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .accountState(user.getAccountState())
                .build();
    }

    public AuthenticationResponse signUp(SignUpRequest request) throws ValidationException, AuthServiceException, InternalServerException {
        //validate the request
        signUpReqValidator.validate(request);

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
                .accountState(AccountState.ACTIVATED)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .accountState(user.getAccountState())
                .build();
    }
}
