package com.kp.springcloud.msvc.users.services.Impl;

import com.kp.springcloud.msvc.users.Repositories.UserRepository;
import com.kp.springcloud.msvc.users.models.entities.Users;
import com.kp.springcloud.msvc.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl  implements UsersService {

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Users> getListUsers() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Users> findByIdUsers(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Users saveUsers(Users users) {
        return userRepository.save(users);
    }

    @Override
    @Transactional
    public void deleteUsersById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> listarByIds(List<Long> ids) {
        return (List<Users>) userRepository.findAllById(ids);
    }
}
