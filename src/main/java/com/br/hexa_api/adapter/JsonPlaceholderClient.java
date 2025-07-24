package com.br.hexa_api.adapter;

import com.br.hexa_api.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "jsonplaceholder", url = "${spring.cloud.openfeign.clients.config.jsonplaceholder.url}")
public interface JsonPlaceholderClient {
    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);

    @GetMapping("/usersParam")
    List<User> getUsersByIdQueryParam(@RequestParam(value = "id") Long id);

}

