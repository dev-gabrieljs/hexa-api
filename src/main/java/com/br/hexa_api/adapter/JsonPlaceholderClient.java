package com.br.hexa_api.adapter;

import com.br.hexa_api.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "jsonplaceholder", url = "${spring.cloud.openfeign.clients.config.jsonplaceholder.url}")
public interface JsonPlaceholderClient {
    @GetMapping("/users")
    List<User> getUsers();
}

