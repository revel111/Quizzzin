package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "\"APPUSER\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 2, max = 50)
    @Column(name = "name")
    private String name;
    @Size(min = 2, max = 50)
    @Column(name = "surname")
    private String surname;
    @Column(name = "email", unique = true) // ?
    private String email;
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="\"APPUSER_ROLE\"",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private Set<UserPuzzleScore> puzzleScores;
    @OneToMany(mappedBy = "user")
    private Set<UserPuzzleRating> puzzleRatings;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
}