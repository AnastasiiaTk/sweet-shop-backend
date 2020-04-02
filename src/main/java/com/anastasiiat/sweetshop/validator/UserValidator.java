package com.anastasiiat.sweetshop.validator;

import com.anastasiiat.sweetshop.dto.UserDTO;
import com.anastasiiat.sweetshop.error.UserCreationException;
import com.anastasiiat.sweetshop.persistence.entity.User;
import com.anastasiiat.sweetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    public void validate(UserDTO userDTO) throws UserCreationException{
        if (userDTO.getUsername().length() < 5 || userDTO.getUsername().length() > 256) {
            throw new UserCreationException(environment.getProperty("size.userForm.username"));
        }
        if (userService.findByUsername(userDTO.getUsername()) != null) {
            throw new UserCreationException(environment.getProperty("duplicate.userForm.username"));
        }
        if (userDTO.getPassword().length() < 5) {
            throw new UserCreationException(environment.getProperty("size.userForm.password"));
        }
    }
}
