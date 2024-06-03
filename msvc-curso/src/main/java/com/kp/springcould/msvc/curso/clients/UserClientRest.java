package com.kp.springcould.msvc.curso.clients;

import com.kp.springcould.msvc.curso.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users",url="localhost:8001")
public interface UserClientRest {
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id);

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto);

    @GetMapping("/usuarios-curso")
    List<UserDto> obtenerAlumnosporCurso(@RequestParam List<Long> ids);

}
