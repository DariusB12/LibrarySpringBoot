package org.example.libraryproject.controller.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.NotifyType;
import org.example.libraryproject.controller.dto.regularDTO.BookDTO;
import org.example.libraryproject.controller.dto.regularDTO.RegularResponse;
import org.example.libraryproject.controller.utils.JsonUtilsConverter;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.Book;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.BookRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService  {
    private final BookRepository bookRepository;
    private final ObservableService observableService;
    private final JsonUtilsConverter jsonUtilsConverter;
    /***
     * Returns a RegularResponse which contains all the distinct books (BookDTO) with their no of pieces
     */
    public RegularResponse getAllBooks() {
        List<Object[]> resultList = bookRepository.findDistinctBooksWithCount();
        return RegularResponse.builder()
                .books(convertObjectBookListToDto(resultList))
                .build();
    }

    /***
     * Convert a list of object books to a list of BookDTO
     * @param resultList List
     * @return List
     */
    private List<BookDTO> convertObjectBookListToDto(List<Object[]> resultList) {
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


    /***
     * find the first available book in the repository which has the same attributes as the book param
     * and mark it as borrowed by the current user
     * @param book Book
     * @throws ValidationException if the book is not available in the DB
     */
    public synchronized Book borrowBook(Book book) throws ValidationException {
        Optional<Book> obj = bookRepository.findFirstAvailableBook(
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishDate(),
                book.getPages(),
                book.getDescription(),
                book.getImagePath());
        User currentUser =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj.isEmpty()){
            throw new ValidationException("the book isn't available");
        }
        Book bookToUpdate = obj.get();
        bookToUpdate.setUser(currentUser);
        Book borrowedBook = bookRepository.save(bookToUpdate);
        observableService.notifyObservers(NotifyType.BOOK_BORROWED);
        return borrowedBook;
    }

    /**
     * Returns a RegularResponse which contains all the Books borrowed (the appointments are confirmed) of a given user
     */
    public RegularResponse getAllConfirmedBorrowBooksUser(String username) {
        List<Book> resultList = bookRepository.findUsersBooksConfirmedBorrowed(username);
        List<BookDTO> userBooksDTO= new ArrayList<>();
        for(Book book : resultList){
            userBooksDTO.add(jsonUtilsConverter.bookToBookDTO(book));
        }
        return RegularResponse.builder()
                .books(userBooksDTO)
                .build();
    }
}
