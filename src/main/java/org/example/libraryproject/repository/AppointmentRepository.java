package org.example.libraryproject.repository;

import org.example.libraryproject.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.user.username = :username")
    List<Appointment> findUserAppointments(@Param("username") String username);

    @Query("SELECT a FROM Appointment a WHERE a.user.username = :username AND a.book.id = :bookID AND a.type = org.example.libraryproject.model.AppointmentType.RETURN")
    Optional<Appointment> findUserAppointment(@Param("username") String username, @Param("bookID") Integer bookID);
}
