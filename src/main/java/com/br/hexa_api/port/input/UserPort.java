package com.br.hexa_api.port.input;





import com.br.hexa_api.domain.model.User;

import java.util.List;

public interface UserPort {

    List<User> fetchUsers();
}
