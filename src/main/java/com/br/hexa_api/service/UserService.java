package com.br.hexa_api.service;

import com.br.hexa_api.adapter.JsonPlaceholderClient;
import com.br.hexa_api.component.UserComponent;
import com.br.hexa_api.domain.model.User;
import com.br.hexa_api.dto.UserDTO;
import com.br.hexa_api.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final UserMapper userMapper;
    private final UserComponent userComponent;

    public UserService(JsonPlaceholderClient jsonPlaceholderClient, UserMapper userMapper, UserComponent userComponent) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
        this.userMapper = userMapper;
        this.userComponent = userComponent;
    }

    public List<UserDTO> getUsers() {
        List<User> users = jsonPlaceholderClient.getUsers();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = jsonPlaceholderClient.getUserById(id);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> searchUsersByName(String name) {
        List<User> users = jsonPlaceholderClient.getUsers();
        List<User> filtered = userComponent.filterUsersByName(users, name);
        return filtered.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
