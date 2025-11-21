package com.leonardorozza.mvgrreparacionesbackend.persistence.entity;

import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;  // Contraseña hasheada con BCrypt

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;   // ADMIN / USER

    @Column(nullable = false)
    private Boolean active = true;
}
