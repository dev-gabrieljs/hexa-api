package com.br.hexa_api.controller;

import com.br.hexa_api.dto.UserDTO;

import java.util.List;

public interface UserControllerInterface {

    List<UserDTO> getUsers();
    UserDTO getUserById(Long id);
    List<UserDTO> searchUsersByName(String name);
    List<UserDTO> getUsersByIdQueryParam(Long id);
}
