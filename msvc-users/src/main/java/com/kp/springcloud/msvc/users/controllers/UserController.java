package com.kp.springcloud.msvc.users.controllers;

import com.kp.springcloud.msvc.users.Repositories.UserRepository;
import com.kp.springcloud.msvc.users.models.entities.Users;
import com.kp.springcloud.msvc.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Users> getUserAll(){
        return usersService.getListUsers();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id){
        Optional <Users> user = usersService.findByIdUsers(id);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody Users users){
        Users savedUsers=usersService.saveUsers(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@RequestBody Users user,@PathVariable Long id){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional <Users> userOp = usersService.findByIdUsers(id);
        Users userDb = userOp.get();
        userDb.setEmail(user.getEmail());
        userDb.setNombre(user.getNombre());
        userDb.setPassword(user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUsers(userDb));

    }

    @GetMapping("/usuarios-curso")
    public ResponseEntity<?> obtenerAlumnosporCurso( @RequestParam List<Long> ids){
        return ResponseEntity.ok(usersService.listarByIds(ids));
    }

//    @PutMapping("/delete/{id}")
//    public ResponseEntity<Users> deleteUser(@RequestBody Users user,@PathVariable Long id){
//        if(!userRepository.existsById(id)){
//            return ResponseEntity.notFound().build();
//        }
//        Optional <Users> userOp = usersService.findByIdUsers(id);
//        Users userDb = userOp.get();
//        userDb.setEmail(user.getEmail());
//        userDb.setId(user.getId());
//        userDb.setNombre(user.getNombre());
//        userDb.setPassword(user.getPassword());
//        userDb.setStatus(0);
//        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUsers(userDb));
//
//    }
}
