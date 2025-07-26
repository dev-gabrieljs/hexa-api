package com.br.hexa_api.component;

import com.br.hexa_api.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserComponent {

    // Filtra usuários pelo nome (case-insensitive)
    public List<User> filterUsersByName(List<User> users, String name) {
        if (users == null || name == null) return List.of();

        return users.stream()
                .filter(Objects::nonNull)
                .filter(user -> user.getName() != null)
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Busca usuário pelo ID com segurança contra null
    public Optional<User> findUserById(List<User> users, Long id) {
        if (users == null || id == null) return Optional.empty();

        return users.stream()
                .filter(Objects::nonNull)
                .filter(user -> Objects.equals(Long.valueOf(user.getId()), id))
                .findFirst();
    }

    // Ordena usuários por nome (case-insensitive)
    public List<User> sortUsersByName(List<User> users) {
        if (users == null) return List.of();

        return users.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(
                        User::getName,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                ))
                .collect(Collectors.toList());
    }

    // Conta quantos usuários têm e-mails corporativos
    public long countCorporateEmails(List<User> users) {
        if (users == null) return 0;

        return users.stream()
                .filter(Objects::nonNull)
                .map(User::getEmail)
                .filter(email -> email != null && email.endsWith(".com"))
                .count();
    }
}
