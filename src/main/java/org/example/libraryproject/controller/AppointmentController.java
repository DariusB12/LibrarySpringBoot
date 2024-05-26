package org.example.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.dto.employeeDTO.EmployeeRequest;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.service.AppointmentService;
import org.example.libraryproject.controller.service.BookService;
import org.example.libraryproject.controller.utils.JsonUtilsConverter;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.AppointmentType;
import org.example.libraryproject.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final BookService bookService;
    private final AppointmentService appointmentService;
    private final JsonUtilsConverter jsonUtilsConverter;

    /***
     * BORROW: borrow a book then, if success, save the appointment with the book marked as borrowed
     * RETURN: save the appointment
     * @param request RegularRequest
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody RegularRequest request) throws ValidationException {
        if(request.getTypeAppointment() == AppointmentType.BORROW){
            System.out.println("received req: " + request);
            Book savedBook = bookService.borrowBook(jsonUtilsConverter.bookDTOToBook(request.getBook()));
            request.setBook(jsonUtilsConverter.bookToBookDTO(savedBook));

            appointmentService.saveAppointment(request);
            return ResponseEntity.ok("appointment made with success");
        }
        if(request.getTypeAppointment() == AppointmentType.RETURN){
            appointmentService.saveAppointment(request);
            return ResponseEntity.ok("appointment made with success");
        }
        return ResponseEntity.badRequest().body("bad type of appointment");
    }

    /**
     * get all appointments
     * @return RegularResponse
     */
    @GetMapping
    public ResponseEntity<?> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.getAll());
    }

    /**
     * get all appointments of a specified user
     * @param username String
     * @return RegularResponse
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserAppointments(@PathVariable("username") String username){
        return ResponseEntity.ok(appointmentService.getUserAppointments(username));
    }

    /**
     * update an appointment (made for borrow) making it confirmed
     * @param request EmployeeRequest
     * @return ResponseEntity
     * @throws ValidationException if the appointment does not exist
     */
    @PutMapping("/borrow")
    public ResponseEntity<?> confirmBorrowAppointment(@RequestBody EmployeeRequest request) throws ValidationException {
        appointmentService.confirmBorrowAppointment(request);
        return ResponseEntity.ok("Appointment confirmed with success");
    }

    /**
     * deletes the appointment and marks the book as available for borrowing only if both exists
     * @param request RegularRequest
     * @return ResponseEntity
     * @throws ValidationException if the book or the appointment does not exist
     */
    @DeleteMapping("/borrow")
    public ResponseEntity<?> cancelBorrowAppointment(@RequestBody RegularRequest request) throws ValidationException {
        appointmentService.cancelBorrowAppointment(request);
        return ResponseEntity.ok("Appointment canceled with success");
    }

    /**
     * marks an appointment as confirmed and marks the book as available for borrowing
     * @param request EmployeeRequest
     * @return ResponseEntity
     * @throws ValidationException if the appointment does not exist
     */
    @PutMapping("/return")
    public ResponseEntity<?> confirmReturnAppointment(@RequestBody EmployeeRequest request) throws ValidationException {
        appointmentService.confirmReturnAppointment(request);
        return ResponseEntity.ok("Appointment confirmed with success");
    }

    /**
     * deletes a return appointment if exists
     * @param request RegularRequest
     * @return ResponseEntity
     * @throws ValidationException if the appointment does not exist
     */
    @DeleteMapping("/return")
    public ResponseEntity<?> cancelReturnAppointment(@RequestBody RegularRequest request) throws ValidationException {
        appointmentService.cancelReturnAppointment(request);
        return ResponseEntity.ok("Appointment canceled with success");
    }
}
