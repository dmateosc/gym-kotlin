package com.cwsdg.cwsdg.service;

import com.cwsdg.cwsdg.controller.model.UserDTO;
import com.cwsdg.cwsdg.mapper.UserMapper;
import com.cwsdg.cwsdg.repository.UserRepository;
import com.cwsdg.cwsdg.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void createUser(String name) {

        User user = new User();
        user.setName(name);

        userRepository.save(user);

    }

    public List<UserDTO> buscarUsuario() {
        List<User> userList = userRepository.findAll();

        return userList.stream().map(UserMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }


}
