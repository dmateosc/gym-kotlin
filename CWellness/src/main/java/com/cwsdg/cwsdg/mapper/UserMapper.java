package com.cwsdg.cwsdg.mapper;


import com.cwsdg.cwsdg.controller.model.UserDTO;
import com.cwsdg.cwsdg.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDto(User user);

    User DtoToEntity(UserDTO userDTO);


}
