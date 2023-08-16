package com.ua.kpi.developmentautomation.mappers;

import com.ua.kpi.developmentautomation.dto.RegisterCredentialsDto;
import com.ua.kpi.developmentautomation.dto.UserDto;
import com.ua.kpi.developmentautomation.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User registerToUser(RegisterCredentialsDto registerCredentialsDto);
}
