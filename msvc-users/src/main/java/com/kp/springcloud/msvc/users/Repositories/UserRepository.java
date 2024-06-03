package com.kp.springcloud.msvc.users.Repositories;

import com.kp.springcloud.msvc.users.models.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,Long> {

}
