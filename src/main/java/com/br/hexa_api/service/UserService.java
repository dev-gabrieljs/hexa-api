package com.br.hexa_api.service;

import com.br.hexa_api.adapter.JsonPlaceholderClient;
import com.br.hexa_api.component.UserComponent;
import com.br.hexa_api.domain.model.User;
import com.br.hexa_api.dto.UserDTO;
import com.br.hexa_api.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    // Retorna todos os usuários convertidos para DTO
    public List<UserDTO> getUsers() {
        List<User> users = Optional.ofNullable(jsonPlaceholderClient.getUsers())
                .orElse(Collections.emptyList());

        return toDTOList(users);
    }

    // Busca usuário por ID, convertendo para DTO
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.ofNullable(jsonPlaceholderClient.getUserById(id))
                .map(userMapper::toDTO);
    }

    // Filtra usuários pelo nome e converte para DTO
    public List<UserDTO> searchUsersByName(String name) {
        List<User> users = Optional.ofNullable(jsonPlaceholderClient.getUsers())
                .orElse(Collections.emptyList());

        List<User> filtered = userComponent.filterUsersByName(users, name);
        return toDTOList(filtered);
    }

    // Método utilitário privado para converter listas
    private List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
