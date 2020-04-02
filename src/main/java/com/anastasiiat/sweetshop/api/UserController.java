package com.anastasiiat.sweetshop.api;

import com.anastasiiat.sweetshop.dto.UserDTO;
import com.anastasiiat.sweetshop.error.UserCreationException;
import com.anastasiiat.sweetshop.service.UserService;
import com.anastasiiat.sweetshop.translator.UserTranslator;
import com.anastasiiat.sweetshop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTranslator userTranslator;

    @Autowired
    private UserValidator userValidator;

    @PostMapping(value = "/api/registration", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody UserDTO userDto) throws UserCreationException {
        userValidator.validate(userDto);
        userService.save(userTranslator.userDtoToEntity(userDto));
    }
}
