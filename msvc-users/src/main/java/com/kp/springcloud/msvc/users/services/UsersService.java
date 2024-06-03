package com.kp.springcloud.msvc.users.services;

import com.kp.springcloud.msvc.users.models.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<Users> getListUsers();

    Optional<Users> findByIdUsers(Long id);

    Users saveUsers(Users users);

    void deleteUsersById(Long id);

    List<Users> listarByIds(List<Long> ids);


}
