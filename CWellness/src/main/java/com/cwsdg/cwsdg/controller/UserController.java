package com.cwsdg.cwsdg.controller;

import com.cwsdg.cwsdg.controller.model.UserDTO;
import com.cwsdg.cwsdg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gym/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void registerUser(@RequestBody UserDTO userDTO){

        userService.createUser(userDTO.getName());

    }

    @GetMapping
    public UserDTO findUsers(){

            return userService.buscarUsuario();

    }

}
