package org.example.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime issued;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
    private AppointmentType type;
    @ManyToOne
    @JoinColumn(name = "terminal_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_terminal"))
    private Terminal terminal;
    private String description;
    private Boolean confirmed;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book"))
    private Book book;
}
