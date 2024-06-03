package com.kp.springcould.msvc.curso.entity;

import com.kp.springcould.msvc.curso.Dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "cursoid")
    private List<CursoUsers> cursoUsersList;

    @Transient
    private List<UserDto> userDto;

    private String nombre;

    private Integer status;

    public void addCursoUser(CursoUsers cursoUsers){
        cursoUsersList.add(cursoUsers);
    }

    public void removeCursoUsers(CursoUsers cursoUsers){
        cursoUsersList.remove(cursoUsers);
    }

}
