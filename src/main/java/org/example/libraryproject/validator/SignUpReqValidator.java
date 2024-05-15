package org.example.libraryproject.validator;

import lombok.AllArgsConstructor;
import org.example.libraryproject.controller.dto.authenticationDTO.SignUpRequest;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.InternalServerException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.example.libraryproject.model.User;
import org.example.libraryproject.model.UserRole;
import org.example.libraryproject.repository.UserRepository;
import org.example.libraryproject.utils.PasswordManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class SignUpReqValidator implements IValidator<SignUpRequest>{
    private final UserRepository userRepository;
    @Override
    public void validate(SignUpRequest entity) throws ValidationException, DataIntegrityViolationException, InternalServerException, AuthServiceException {

        String errors = "";
        //verify the secret password if the ROLE is EMPLOYEE
        if (entity.getRole() == UserRole.EMPLOYEE) {
            boolean validPassword = false;
            try {
                String pass = PasswordManager.getEmployeePassword();
                if (pass == null) {
                    throw new InternalServerException("Cannot read app.properties file");
                }
                validPassword = entity.getRolePassword().matches(pass);
            } catch (IOException | InternalServerException e) {
                throw new InternalServerException("Cannot read app.properties file");
            }

            if (!validPassword) {
                throw new AuthServiceException("Invalid employee password");
            }
        }

        //verify if the unique constrains on columns are violated
        Optional<User> objUsername = userRepository.findByUsername(entity.getUsername());
        Optional<User> objEmail = userRepository.findByEmail(entity.getEmail());
        Optional<User> objCnp = userRepository.findByCnp(entity.getCnp());
        if (objUsername.isPresent()) {
            errors += "Username already used\n";
        }
        if (objEmail.isPresent()) {
            errors += "Email already used\n";
        }
        if (objCnp.isPresent()) {
            errors += "CNP already used\n";
        }
        if (!errors.isEmpty()) {
            throw new DataIntegrityViolationException(errors);
        }

        //validate the user
        if(entity.getUsername() == "" ||entity.getUsername() == null ){
            errors += "invalid username\n";
        }
        if(entity.getPassword()=="" || entity.getPassword()==null ){
            errors += "invalid password\n";
        }
        if(entity.getFirstName()=="" || entity.getFirstName()==null){
            errors += "invalid firstName\n";
        }
        if(entity.getLastName()==""|| entity.getLastName()== null){
            errors += "invalid lastName\n";
        }
        if(entity.getAddress()=="" || entity.getAddress() == null){
            errors += "invalid address\n";
        }

        Pattern pattern = Pattern.compile("[a-zA-Z]");
        if(pattern.matcher(entity.getPhone()).find()){
            errors+="invalid phone\n";
        }
        if(pattern.matcher(entity.getCnp()).find()){
            errors+="invalid cnp\n";
        }
        if(!entity.getEmail().contains("@")){
            errors += "invalid email\n";
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
