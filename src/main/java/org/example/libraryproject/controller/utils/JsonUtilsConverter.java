package org.example.libraryproject.controller.utils;

import lombok.AllArgsConstructor;
import org.example.libraryproject.controller.dto.regularDTO.AppointmentDTO;
import org.example.libraryproject.controller.dto.regularDTO.BookDTO;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.dto.regularDTO.TerminalDTO;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.*;
import org.example.libraryproject.repository.BookRepository;
import org.example.libraryproject.repository.TerminalRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JsonUtilsConverter {
    private final TerminalRepository terminalRepository;
    private final BookRepository bookRepository;

    /**
     * Convert a terminal to a terminalDTO
     * @param terminal Terminal
     * @return TerminalDTO
     */
    public TerminalDTO terminalToTerminalDTO(Terminal terminal){
        return TerminalDTO.builder()
                .id(terminal.getId())
                .name(terminal.getName())
                .location(terminal.getLocation())
                .terminalType(terminal.getTerminalType())
                .build();
    }
    /**
     * Extract the Appointment from a request
     * @param request RegularRequest
     * @return Appointment
     */
    public Appointment regularRequestToAppointment(RegularRequest request) throws ValidationException {
        User currentUser =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TerminalDTO terminalDTO = request.getTerminal();
        Optional<Terminal> obj = terminalRepository.findById(terminalDTO.getId());
        BookDTO bookDTO = request.getBook();
        Optional<Book> obj2 = bookRepository.findById(bookDTO.getId());
        if(obj.isPresent()){
            if(obj2.isPresent()){
                return Appointment.builder()
                        .issued(LocalDateTime.now())
                        .user(currentUser)
                        .type(request.getTypeAppointment())
                        .terminal(obj.get())
                        .description(request.getDescription())
                        .confirmed(false)
                        .book(obj2.get())
                        .build();
            }else{
                throw new ValidationException("book not found");
            }
        }
        else{
            throw new ValidationException("terminal not found");
        }
    }

    /**
     * convert an appointment to appointmentDTO
     * @param appointment Appointment
     * @return AppointmentDTO
     */
    public AppointmentDTO appointmenttoAppointmentDTO(Appointment appointment){
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .issued(appointment.getIssued())
                .username(appointment.getUser().getUsername())
                .type(appointment.getType())
                .terminal(terminalToTerminalDTO(appointment.getTerminal()))
                .description(appointment.getDescription())
                .confirmed(appointment.getConfirmed())
                .book(bookToBookDTO(appointment.getBook()))
                .build();
    }

    /**
     * convert a bookDTO to book (with null user)
     * @param bookDTO BookDTO
     * @return Book
     */
    public Book bookDTOToBook(BookDTO bookDTO){
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .publisher(bookDTO.getPublisher())
                .publishDate(bookDTO.getPublishDate())
                .pages(bookDTO.getPages())
                .description(bookDTO.getDescription())
                .imagePath(bookDTO.getImagePath())
                .build();
    }

    /**
     * convert a book to bookDTO
     * @param book Book
     * @return BookDTO
     */
    public BookDTO bookToBookDTO(Book book){
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publishDate(book.getPublishDate())
                .pages(book.getPages())
                .description(book.getDescription())
                .imagePath(book.getImagePath())
                .build();
    }

}
