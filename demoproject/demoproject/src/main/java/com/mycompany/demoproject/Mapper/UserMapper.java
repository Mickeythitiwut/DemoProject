package com.mycompany.demoproject.Mapper;

import com.mycompany.demoproject.Entity.User;
import com.mycompany.demoproject.Model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterResponse toRegisterResponse(User user);
}
