package org.example.libraryproject.controller.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.config.JwtService;
import org.example.libraryproject.controller.dto.authenticationDTO.AuthenticationResponse;
import org.example.libraryproject.controller.dto.authenticationDTO.SignInRequest;
import org.example.libraryproject.controller.dto.authenticationDTO.SignUpRequest;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.InternalServerException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.UserRepository;
import org.example.libraryproject.validator.IValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IValidator<SignUpRequest> signUpReqValidator;
    public Set<String> connectedUsersUsernames = ConcurrentHashMap.newKeySet();

    /**
     * authenticate a user and generate JWT token
     * @return AuthenticationResponse with the JWT token generated
     * @throws AuthServiceException if the use is already logged in or if the credentials are wrong
     */
    public AuthenticationResponse signIn(SignInRequest request) throws AuthServiceException {
        if (connectedUsersUsernames.contains(request.getUsername())) {
            throw new AuthServiceException("user already logged in");
        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthServiceException("username not found"));
        String token = jwtService.generateToken(user);
        //add in the logged in the users list
        connectedUsersUsernames.add(request.getUsername());
        System.out.println("user: " + request.getUsername() + "registered");
        return AuthenticationResponse.builder()
                .token(token)
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .build();
    }

    /**
     * removes the username, of the currently connected user, from the connectedUsers list
     */
    public void logOutUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            connectedUsersUsernames.remove(((User) principal).getUsername());
            System.out.println("user: " + ((User) principal).getUsername() + "logged out");
        }
    }

    /**
     * sign up a new user
     * @return AuthenticationResponse with the newly registered user
     * @throws ValidationException if the credentials are invalid
     * @throws AuthServiceException if the employee password is incorrect(in case of an EMPLOYEE registration)
     * @throws InternalServerException cannot retrieve the employee passwd from the .properties file
     */
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
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        connectedUsersUsernames.add(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .build();
    }

}
