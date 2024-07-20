package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "\"APPUSER\"")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 2, max = 50)
    @NotBlank
    @Column(name = "name")
    private String name;
    @Size(min = 2, max = 50)
    @NotBlank
    @Column(name = "surname")
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Size(min = 1, max = 25)
    @NotBlank
    @Column(name = "nickname")
    private String nickname;
    @Email
    @Column(name = "email", unique = true)
    private String email;
    //    @Pattern.List({
//            @Pattern(regexp = ".*[0-9].*"),
//            @Pattern(regexp = ".*[a-z].*"),
//            @Pattern(regexp = ".*[A-Z].*"),
//            @Pattern(regexp = ".*[@#$%^&+=!].*"),
//            @Pattern(regexp = "\\S+"),
//            @Pattern(regexp = ".{8,}")
//    })
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "\"APPUSER_ROLE\"",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<UserPuzzleScore> puzzleScores;
    @OneToMany(mappedBy = "user")
    private Set<UserPuzzleRating> puzzleRatings;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getName().name())).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return nickname;
    }
}