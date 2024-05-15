package org.example.libraryproject.controller.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.regularDTO.BookDTO;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.dto.regularDTO.RegularResponse;
import org.example.libraryproject.exception.exceptions.UserServiceException;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.BookRepository;
import org.example.libraryproject.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RegularService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    /***
        Verify the validity of the password of the user
        If valid, then delete the user account
     */
    public RegularResponse deleteAccount(RegularRequest request) throws UserServiceException {
        User user = getCurrentUser();
        if(request.getPassword() != null && passwordMatch(request.getPassword(),user.getPassword())){
            userRepository.delete(user);
            return RegularResponse.builder().message("account deleted").build();
        }else{
            throw new UserServiceException("wrong credentials");
        }
    }

    /***
     * Return the current user (based on the JWT with which was made the request)
     */
    private User getCurrentUser() {
        // Get the currently logged-in user from SecurityContext
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private boolean passwordMatch(String passwdToCompare,String password){
        return passwordEncoder.matches(passwdToCompare,password);
    }

    /***
     * Returns a RegularResponse which contains all the distinct books (BookDTO)
     */
    public RegularResponse getAllBooks() {
        List<Object[]> resultList = bookRepository.findDistinctBooksWithCount();
        return RegularResponse.builder()
                .books(convertBookListToDto(resultList))
                .build();
    }
    private List<BookDTO> convertBookListToDto(List<Object[]> resultList) {
        List<BookDTO> bookDtos = new ArrayList<>();
        for (Object[] result : resultList) {
            BookDTO bookDto = BookDTO.builder()
                    .title(result[0].toString())
                    .author(result[1].toString())
                    .publisher(result[2].toString())
                    .publishDate(LocalDate.parse(result[3].toString()))
                    .pages(Integer.valueOf(result[4].toString()))
                    .description(result[5].toString())
                    .imagePath(result[6].toString())
                    .booksAvailable(Integer.valueOf(result[7].toString()))
                    .build();
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

}
