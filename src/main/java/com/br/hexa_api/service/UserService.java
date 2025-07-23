package com.br.hexa_api.service;


import com.br.hexa_api.adapter.JsonPlaceholderClient;
import com.br.hexa_api.domain.model.User;
import com.br.hexa_api.dto.UserDTO;
import com.br.hexa_api.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final UserMapper userMapper;


    public UserService(JsonPlaceholderClient jsonPlaceholderClient, UserMapper userMapper) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
        this.userMapper = userMapper;
    }

    // Método de serviço que retorna uma lista de UserDTOs
    public List<UserDTO> getUsers() {
        List<User> users = jsonPlaceholderClient.getUsers();
        return users.stream()
                .map(userMapper::toDTO) // Convertendo User para UserDTO
                .collect(Collectors.toList());
    }
}

