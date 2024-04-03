package org.example.libraryproject.validator;

import org.example.libraryproject.exception.ValidationException;
import org.example.libraryproject.model.User;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserValidator implements IValidator<User>{
    @Override
    public void validate(User entity) throws ValidationException {
        String errors = "";
        if(entity.getUsername().isEmpty() || Objects.equals(entity.getUsername(), "")){
            errors += "invalid username; ";
        }
        if(entity.getPassword().isEmpty() || Objects.equals(entity.getPassword(), "") ){
            errors += "invalid password; ";
        }
        if(entity.getFirstName().isEmpty() || Objects.equals(entity.getFirstName(), "")){
            errors += "invalid firstName;";
        }
        if(entity.getLastName().isEmpty() || Objects.equals(entity.getLastName(), "") ){
            errors += "invalid lastName; ";
        }
        if(entity.getAddress().isEmpty() || Objects.equals(entity.getAddress(), "")){
            errors += "invalid address; ";
        }

        Pattern pattern = Pattern.compile("[a-zA-Z]");
        if(pattern.matcher(entity.getPhone()).find()){
            errors+="invalid phone; ";
        }
        if(pattern.matcher(entity.getCnp()).find()){
            errors+="invalid cnp; ";
        }
        if(!entity.getEmail().contains("@")){
            errors += "invalid email; ";
        }
        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
