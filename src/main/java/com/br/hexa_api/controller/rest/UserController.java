package com.br.hexa_api.controller.rest;

import com.br.hexa_api.controller.UserControllerInterface;
import com.br.hexa_api.service.UserService;
import com.br.hexa_api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserControllerInterface {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/usersParam")
    public List<UserDTO> getUsersByIdQueryParam(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            UserDTO user = userService.getUserById(id);
            return List.of(user);
        } else {
            return userService.getUsers();
        }
    }




    @GetMapping("/users/{id}")
    @Override
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @GetMapping("/users/search")
    @Override
    public List<UserDTO> searchUsersByName(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

}
