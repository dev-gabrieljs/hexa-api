package com.br.hexa_api.component;

import com.br.hexa_api.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserComponent {

    // Filtrar usuários por parte do nome
    public List<User> filterUsersByName(List<User> users, String name) {
        return users.stream()
                .filter(user -> user.getName() != null && user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Encontrar usuário por ID (em lista)
    public Optional<User> findUserById(List<User> users, Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    // Ordenar usuários por nome
    public List<User> sortUsersByName(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // Contar usuários com email corporativo
    public long countCorporateEmails(List<User> users) {
        return users.stream()
                .filter(user -> user.getEmail() != null && user.getEmail().endsWith(".com"))
                .count();
    }
}
