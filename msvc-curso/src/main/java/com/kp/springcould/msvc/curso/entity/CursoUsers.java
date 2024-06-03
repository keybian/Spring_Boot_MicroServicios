package com.kp.springcould.msvc.curso.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursousers")
public class CursoUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid",unique = true)
    private Long userid;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursoUsers that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(userid, that.userid);
    }


}
