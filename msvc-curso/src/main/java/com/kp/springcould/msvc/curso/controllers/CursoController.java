package com.kp.springcould.msvc.curso.controllers;

import com.kp.springcould.msvc.curso.Dto.UserDto;
import com.kp.springcould.msvc.curso.entity.Curso;
import com.kp.springcould.msvc.curso.repositories.CursoRepository;
import com.kp.springcould.msvc.curso.services.CursoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/")
    public List<Curso> getCursos(){
        return cursoService.getAllCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id){
        Optional <Curso> curso = cursoService.getCursoByIdWithUser(id);
        return curso.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso){
        Curso savedCurso=cursoService.saveCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Curso> editCurso(@RequestBody Curso curso,@PathVariable Long id){
        if(!cursoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional <Curso> cursoOP=cursoService.getCursoById(id);
        Curso cursoDB = cursoOP.get();
        cursoDB.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.saveCurso(cursoDB));
    }

    @PostMapping("/deletecurso/{id}")
    public ResponseEntity<Curso> deleteCursoById( @PathVariable Long id){
        if(!cursoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional <Curso> cursoOP=cursoService.getCursoById(id);
        Curso cursoDB = cursoOP.get();
        cursoDB.setStatus(2);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.saveCurso(cursoDB));
    }

    @PutMapping("/asignar-user/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody UserDto userDto,@PathVariable Long cursoId){
        Optional<UserDto> o;
        try{
            o = cursoService.asigUser(userDto,cursoId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","error en la comunicacion:" + e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping("/crear-user/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody UserDto userDto,@PathVariable Long cursoId){
        Optional<UserDto> o;
        try{
            o = cursoService.addUser(userDto,cursoId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","error en la comunicacion:" + e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();

    }
}
