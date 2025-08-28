package com.altairum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private short id;

    @Column(name = "role_name", nullable = false, unique = true, length = 5)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<User> user;

    public Role(RoleEnum name) {
        this.name = name;
    }
}
