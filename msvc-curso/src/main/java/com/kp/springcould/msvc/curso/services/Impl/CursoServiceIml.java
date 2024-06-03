package com.kp.springcould.msvc.curso.services.Impl;

import com.kp.springcould.msvc.curso.Dto.UserDto;
import com.kp.springcould.msvc.curso.clients.UserClientRest;
import com.kp.springcould.msvc.curso.entity.Curso;
import com.kp.springcould.msvc.curso.entity.CursoUsers;
import com.kp.springcould.msvc.curso.repositories.CursoRepository;
import com.kp.springcould.msvc.curso.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceIml implements CursoService {

    @Autowired
    UserClientRest userClientRest;
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> getAllCursos() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> getCursoByIdWithUser(Long id) {
        Optional<Curso> o = cursoRepository.findById(id);
        if(o.isPresent()){
            Curso curso = o.get();
            if(!curso.getCursoUsersList().isEmpty()){
                List<Long> ids = curso.getCursoUsersList().stream().map(CursoUsers::getUserid)
                        .toList();
                List<UserDto> usurios = userClientRest.obtenerAlumnosporCurso(ids);
                curso.setUserDto(usurios);
            }
            return Optional.of(curso);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Optional<UserDto> asigUser(UserDto userDto, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);

        if(o.isPresent()){
            UserDto userDtoMsvc = userClientRest.getUser(userDto.getId());
            Curso curso =  o.get();
            CursoUsers cursoUsers = new CursoUsers();
            cursoUsers.setUserid(userDtoMsvc.getId());
            curso.addCursoUser(cursoUsers);
            cursoRepository.save(curso);
            return Optional.of(userDtoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UserDto> addUser(UserDto userDto, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);

        if(o.isPresent()){
            UserDto userDtoMsvc = userClientRest.createUser(userDto);
            Curso curso =  o.get();
            CursoUsers cursoUsers = new CursoUsers();
            cursoUsers.setUserid(userDtoMsvc.getId());
            curso.addCursoUser(cursoUsers);
            cursoRepository.save(curso);
            return Optional.of(userDtoMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UserDto> unAsigUser(UserDto userDto, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);

        if(o.isPresent()){
            UserDto userDtoMsvc = userClientRest.getUser(userDto.getId());
            Curso curso =  o.get();
            CursoUsers cursoUsers = new CursoUsers();
            cursoUsers.setUserid(userDtoMsvc.getId());
            curso.removeCursoUsers(cursoUsers);
            cursoRepository.save(curso);
            return Optional.of(userDtoMsvc);
        }
        return Optional.empty();
    }
}
