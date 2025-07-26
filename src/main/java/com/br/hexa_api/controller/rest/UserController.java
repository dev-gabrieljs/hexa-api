package com.br.hexa_api.controller.rest;

import com.br.hexa_api.controller.UserControllerInterface;
import com.br.hexa_api.dto.UserDTO;
import com.br.hexa_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Define prefixo comum
public class UserController implements UserControllerInterface {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public List<UserDTO> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(users).getBody();
    }

    @GetMapping("/search")
    @Override
    public List<UserDTO> searchUsersByName(@RequestParam String name) {
        List<UserDTO> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users).getBody();
    }

    @GetMapping("/{id}")
    @Override
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    @Override
    @GetMapping("/param")
    public List<UserDTO> getUsersByIdQueryParam(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return userService.getUserById(id)
                    .map(List::of)
                    .orElse(List.of());
        } else {
            return userService.getUsers();
        }
    }


}
