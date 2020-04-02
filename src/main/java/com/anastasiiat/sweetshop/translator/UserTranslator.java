package com.anastasiiat.sweetshop.translator;

import com.anastasiiat.sweetshop.dto.UserDTO;
import com.anastasiiat.sweetshop.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserTranslator {

    public User userDtoToEntity(UserDTO source) {
        User destination = new User();
        destination.setUsername(source.getUsername());
        destination.setPassword(source.getPassword());
        return destination;
    }
}
