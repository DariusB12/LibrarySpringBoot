package org.example.libraryproject.controller.dto.authenticationDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.libraryproject.model.AccountState;
import org.example.libraryproject.model.UserRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("token")
    private String token;
    @JsonProperty("username")
    private String username;
    @JsonProperty("user role")
    private UserRole userRole;
    @JsonProperty("account state")
    private AccountState accountState;
}
