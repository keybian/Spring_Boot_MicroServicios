package com.kp.springcloud.msvc.users.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    private Integer status;


}
