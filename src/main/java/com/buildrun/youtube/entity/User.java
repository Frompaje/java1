package com.buildrun.youtube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createAT")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();

    public User(UUID uuid, String username, String email, String password, LocalDateTime now, LocalDateTime now1) {
    }
}
