package org.example.libraryproject.controller.service;

import com.sun.source.tree.LambdaExpressionTree;
import lombok.RequiredArgsConstructor;
import org.example.libraryproject.controller.AppointmentController;
import org.example.libraryproject.controller.dto.NotifyType;
import org.example.libraryproject.controller.dto.employeeDTO.EmployeeRequest;
import org.example.libraryproject.controller.dto.regularDTO.AppointmentDTO;
import org.example.libraryproject.controller.dto.regularDTO.BookDTO;
import org.example.libraryproject.controller.dto.regularDTO.RegularRequest;
import org.example.libraryproject.controller.dto.regularDTO.RegularResponse;
import org.example.libraryproject.controller.utils.JsonUtilsConverter;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.Appointment;
import org.example.libraryproject.model.AppointmentType;
import org.example.libraryproject.model.Book;
import org.example.libraryproject.model.User;
import org.example.libraryproject.repository.AppointmentRepository;
import org.example.libraryproject.repository.BookRepository;
import org.example.libraryproject.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    public final BookRepository bookRepository;
    private final JsonUtilsConverter jsonUtilsConverter;
    private final ObservableService observableService;

    /**
     * save the appointment within a request
     * @param request RegularRequest
     */
    public void saveAppointment(RegularRequest request) throws ValidationException {
        Appointment appointment = jsonUtilsConverter.regularRequestToAppointment(request);
        Optional<Appointment> obj = appointmentRepository.findUserAppointment(appointment.getUser().getUsername(),appointment.getBook().getId());
        if(obj.isPresent()){
            throw new ValidationException("the return appointment already exists");
        }
        appointmentRepository.save(appointment);
        observableService.notifyObservers(NotifyType.APPOINTMENT_CREATED);
    }

    /**
     * returns a response with all the appointments in the DB
     * @return RegularResponse
     */
    public RegularResponse getAll() {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<AppointmentDTO> allAppointmentsDTO = new ArrayList<>();
        for(Appointment appointment : allAppointments){
            allAppointmentsDTO.add(jsonUtilsConverter.appointmenttoAppointmentDTO(appointment));
        }
        return RegularResponse.builder()
                .appointments(allAppointmentsDTO)
                .build();
    }

    /**
     * returns a response with all the appointments of a specified user
     * @param username String
     * @return RegularResponse
     */
    public RegularResponse getUserAppointments(String username) {
        List<Appointment> allAppointmentsUser = appointmentRepository.findUserAppointments(username);

        List<AppointmentDTO> allAppointmentsDTOUser = new ArrayList<>();
        for(Appointment appointment : allAppointmentsUser){
            allAppointmentsDTOUser.add(jsonUtilsConverter.appointmenttoAppointmentDTO(appointment));
        }
        return RegularResponse.builder()
                .appointments(allAppointmentsDTOUser)
                .build();
    }

    /**
     * confirm an appointment made for borrow
     * @param request EmployeeRequest
     * @throws ValidationException if the appointment does not exist
     */
    public void confirmBorrowAppointment(EmployeeRequest request) throws ValidationException {
        Optional<Appointment> obj = appointmentRepository.findById(request.getAppointment().getId());
        if(obj.isPresent() && obj.get().getType() != AppointmentType.BORROW){
            throw new ValidationException("appointment type is not borrow");
        }
        if(obj.isPresent()){
            Appointment appointment = obj.get();
            appointment.setConfirmed(true);
            appointmentRepository.save(appointment);
            observableService.notifyObservers(NotifyType.APPOINTMENT_CONFIRMED);
        }
        else{
            throw new ValidationException("the appointment doesn't exist");
        }
    }


    /**
     * deletes an appointment and updates a book making it again available
     * @param request EmployeeRequest
     * @throws ValidationException if the appointment does not exist
     */
    public void cancelBorrowAppointment(RegularRequest request) throws ValidationException {
        Optional<Appointment> obj2 = appointmentRepository.findById(request.getAppointment().getId());
        Optional<Book> obj  = bookRepository.findById(request.getAppointment().getBook().getId());
        if(obj2.isPresent() && obj2.get().getType() != AppointmentType.BORROW){
            throw new ValidationException("appointment type is not borrow");
        }
        if(obj2.isEmpty()){
            throw new ValidationException("appointment does not exist");
        }
        if(obj.isEmpty()){
            throw new ValidationException("book does not exist");
        }
        appointmentRepository.delete(obj2.get());
        Book book = obj.get();
        book.setUser(null);
        bookRepository.save(book);
        observableService.notifyObservers(NotifyType.APPOINTMENT_CANCELED);
    }

    /**
     * * mark an appointment as confirmed and marks the book as not borrowed
     * @param request EmployeeRequest
     * @throws ValidationException if the appointment of the book does not exist
     */
    public void confirmReturnAppointment(EmployeeRequest request) throws ValidationException {
        Optional<Appointment> obj2 = appointmentRepository.findById(request.getAppointment().getId());
        Optional<Book> obj  = bookRepository.findById(request.getAppointment().getBook().getId());
        if(obj2.isPresent() && obj2.get().getType() != AppointmentType.RETURN){
            throw new ValidationException("appointment type is not return");
        }
        if(obj2.isEmpty()){
            throw new ValidationException("appointment does not exist");
        }
        if(obj.isEmpty()){
            throw new ValidationException("book does not exist");
        }
        Appointment appointment = obj2.get();
        appointment.setConfirmed(true);
        appointmentRepository.save(appointment);
        Book book = obj.get();
        book.setUser(null);
        bookRepository.save(book);
        observableService.notifyObservers(NotifyType.APPOINTMENT_CONFIRMED);
    }

    /**
     * only deletes the appointment (the book still remains taken, because is a RETURN appointment)
     * @param request RegularRequest
     */
    public void cancelReturnAppointment(RegularRequest request) throws ValidationException {
        Optional<Appointment> obj = appointmentRepository.findById(request.getAppointment().getId());
        if(obj.isPresent() && obj.get().getType() != AppointmentType.RETURN){
            throw new ValidationException("appointment type is not return");
        }
        if(obj.isEmpty()){
            throw new ValidationException("appointment does not exist");
        }
        appointmentRepository.delete(obj.get());
        observableService.notifyObservers(NotifyType.APPOINTMENT_CANCELED);
    }
}
