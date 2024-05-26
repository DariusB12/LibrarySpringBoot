package org.example.libraryproject.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.libraryproject.controller.dto.NotifyType;
import org.example.libraryproject.controller.service.AuthService;
import org.example.libraryproject.controller.service.ObservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class ObserverController {
    private final ObservableService observableService;
    private final AuthService authService;
    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return observableService.addObserver();
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<Void> logout() {
        authService.logOutUser();
        observableService.removeObserver();
        return ResponseEntity.ok().build();
    }
}