package org.example.libraryproject.controller.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.example.libraryproject.controller.dto.NotifyType;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.dto.regularDTO.RegularResponse;
import org.example.libraryproject.exception.exceptions.UserServiceException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.Appointment;
import org.example.libraryproject.model.Book;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.AppointmentRepository;
import org.example.libraryproject.repository.BookRepository;
import org.example.libraryproject.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RegularService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final AppointmentRepository appointmentRepository;
    private final ObservableService observableService;

    /**
     * If the user's password matches and if the user does not have books borrowed,
     * then delete the user account (with its appointments if he has)
     * @param request RegularRequest
     * @throws UserServiceException if the credentials are wrong
     * @throws ValidationException if the user still has borrowed books
     */
    public RegularResponse deleteAccount(RegularRequest request) throws UserServiceException, ValidationException {
        User user = getCurrentUser();
        if(request.getPassword() != null && passwordMatch(request.getPassword(),user.getPassword())){
             if(bookRepository.findUsersBooksConfirmedBorrowed(user.getUsername()).isEmpty()){
                 //deletes all the appointments
                 List<Appointment> allUsersAppointmentsReferred = appointmentRepository.findUserAppointments(user.getUsername());
                 appointmentRepository.deleteAll(allUsersAppointmentsReferred);
                 //updates all the books which refer the user_id as null (available)
                 //the books may refer the user because BORROW appointments were made but not confirmed
                 List<Book> allReferredBooks = bookRepository.findBooksReferredUser(user.getUsername());
                 for(Book book:allReferredBooks){
                     book.setUser(null);
                     bookRepository.save(book);
                 }
                 userRepository.delete(user);
                 observableService.notifyObservers(NotifyType.USER_DELETED);
                 return RegularResponse.builder().message("account deleted").build();
             }else{
                 throw new ValidationException("not all the books have been returned");
             }
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
}
