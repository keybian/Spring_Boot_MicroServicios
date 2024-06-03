package com.kp.springcould.msvc.curso.services;

import com.kp.springcould.msvc.curso.Dto.UserDto;
import com.kp.springcould.msvc.curso.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> getAllCursos();
    Optional<Curso> getCursoById(Long id);
    Optional<Curso> getCursoByIdWithUser(Long id);

    Curso saveCurso(Curso curso);

    Optional<UserDto> asigUser(UserDto userDto,Long idCurso);

    Optional<UserDto> addUser(UserDto userDto, Long idCurso);

    Optional<UserDto> unAsigUser(UserDto userDto,Long idCurso);
}
