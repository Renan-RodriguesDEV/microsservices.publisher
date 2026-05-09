package com.micoservice.publisher.domain.dto.request;

import com.micoservice.publisher.domain.dto.enums.RoleEnum;
import com.micoservice.publisher.domain.model.User;

public record UserDTO(String username, String password, RoleEnum role) {
    public User toEntity(){
        return new User(username,password,role);
    }
}
