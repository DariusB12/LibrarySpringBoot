package org.example.libraryproject;

import lombok.extern.flogger.Flogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.libraryproject.model.Book;
import org.example.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }


}
