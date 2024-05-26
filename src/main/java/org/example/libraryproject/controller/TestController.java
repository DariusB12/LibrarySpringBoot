package org.example.libraryproject.controller;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.employeeDTO.EmployeeRequest;
import org.example.libraryproject.controller.dto.regularDTO.AppointmentDTO;
import org.example.libraryproject.controller.dto.regularDTO.BookDTO;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.dto.regularDTO.TerminalDTO;
import org.example.libraryproject.controller.utils.JsonUtilsConverter;
import org.example.libraryproject.model.*;
import org.example.libraryproject.repository.BookRepository;
import org.example.libraryproject.repository.TerminalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final BookRepository bookRepository;
    private final TerminalRepository terminalRepository;
    private final JsonUtilsConverter jsonUtilsConverter;
    @PostMapping("/populate")
    public void populate(){
        //populate with books
        Book book = Book.builder()
                .title("Moara Cu Noroc")
                .author("Ioan Slavici")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(130)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\DesignOfBooks.png")
                .build();
        Book book2 = Book.builder()
                .title("Moara Cu Noroc")
                .author("Ioan Slavici")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(130)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\DesignOfBooks.png")
                .build();
        Book book3 = Book.builder()
                .title("Moara Cu Noroc")
                .author("Ioan Slavici")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(130)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\DesignOfBooks.png")
                .build();
        Book book4 = Book.builder()
                .title("Moara Cu Noroc")
                .author("Ioan Slavici")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(130)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\DesignOfBooks.png")
                .build();
        Book book5 = Book.builder()
                .title("Moara Cu Noroc")
                .author("Ioan Slavici")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(130)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\DesignOfBooks.png")
                .build();
        bookRepository.save(book);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        Book book6 = Book.builder()
                .title("Alice in tara minunilor")
                .author("Mihai Emi")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(2443)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotter.jpg")
                .build();
        Book book7 = Book.builder()
                .title("Alice in tara minunilor")
                .author("Mihai Emi")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(2443)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotter.jpg")
                .build();
        Book book8 = Book.builder()
                .title("Alice in tara minunilor")
                .author("Mihai Emi")
                .publisher("Agenda Zilnica")
                .publishDate(LocalDate.now())
                .pages(2443)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotter.jpg")
                .build();
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);

        Book book9 = Book.builder()
                .title("Dincolo de amintiri")
                .author("Darius Bordeanu")
                .publisher("Paralela 45")
                .publishDate(LocalDate.now())
                .pages(344)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotterChamberOfSecrets.jpg")
                .build();
        Book book10 = Book.builder()
                .title("Dincolo de amintiri")
                .author("Darius Bordeanu")
                .publisher("Paralela 45")
                .publishDate(LocalDate.now())
                .pages(344)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotterChamberOfSecrets.jpg")
                .build();
        Book book11 = Book.builder()
                .title("Dincolo de amintiri")
                .author("Darius Bordeanu")
                .publisher("Paralela 45")
                .publishDate(LocalDate.now())
                .pages(344)
                .description("no description because i have no inspiration")
                .imagePath("C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotterChamberOfSecrets.jpg")
                .build();
        bookRepository.save(book9);
        bookRepository.save(book10);
        bookRepository.save(book11);

        //populate terminals
        Terminal terminal1 = Terminal.builder()
                .name("terminal1")
                .location("BCU")
                .terminalType(TerminalType.BORROW)
                .build();
        Terminal terminal3 = Terminal.builder()
                .name("terminal2")
                .location("BCU")
                .terminalType(TerminalType.BORROW)
                .build();
        Terminal terminal4 = Terminal.builder()
                .name("terminal3")
                .location("FSEGA")
                .terminalType(TerminalType.BORROW)
                .build();
        Terminal terminal2 = Terminal.builder()
                .name("Return Terminal")
                .location("BCU")
                .terminalType(TerminalType.RETURN)
                .build();
        terminalRepository.save(terminal1);
        terminalRepository.save(terminal2);
        terminalRepository.save(terminal3);
        terminalRepository.save(terminal4);
    }
    @GetMapping("/getRequest")
    public RegularRequest getRegularRequest(){
        Terminal terminal = Terminal.builder()
                .id(1)
                .location("BCU")
                .name("terminal1")
                .terminalType(TerminalType.BORROW)
                .build();
        Optional<Book> book = bookRepository.findFirstAvailableBook("Dincolo de amintiri",
                "Darius Bordeanu","Paralela 45",LocalDate.now(),344,"no description because i have no inspiration",
                "C:\\Users\\DariusName\\Desktop\\UBB sem4\\ISS\\LibraryProject\\BooksImagesDontMove\\HarryPotterChamberOfSecrets.jpg");
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .id(2)
                .type(AppointmentType.RETURN)
                .build();

        if(book.isPresent()) {
            return RegularRequest.builder()
                    .terminal(jsonUtilsConverter.terminalToTerminalDTO(terminal))
                    .book(jsonUtilsConverter.bookToBookDTO(book.get()))
                    .appointment(appointmentDTO)
                    .build();
        }else{
            return RegularRequest.builder()
                    .terminal(jsonUtilsConverter.terminalToTerminalDTO(terminal))
                    .book(null)
                    .build();
        }
    }

    @GetMapping("/getEmployeeReq")
    public ResponseEntity<EmployeeRequest> getEmployeeRequest(){
        BookDTO bookDTO = jsonUtilsConverter.bookToBookDTO(bookRepository.findAll().get(0));
        TerminalDTO terminalDTO = jsonUtilsConverter.terminalToTerminalDTO(terminalRepository.findAll().get(0));
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .terminal(terminalDTO)
                .book(bookDTO)
                .build();
        return ResponseEntity.ok(EmployeeRequest.builder()
                .appointment(appointmentDTO)
                .build());
    }
}
