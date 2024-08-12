package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The User class represents an application user.
 * It implements {@link UserDetails} to integrate with Spring Security for user authentication and authorization.
 * This class is mapped to the "APPUSER" table in the database and contains user-related information.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "\"APPUSER\"")
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     * This field is auto-generated and serves as the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The user's first name.
     * This field is required to be between 2 and 50 characters.
     */
    @Size(min = 2, max = 50)
    @NotBlank
    @Column(name = "name")
    private String name;

    /**
     * The user's last name.
     * This field is required to be between 2 and 50 characters.
     */
    @Size(min = 2, max = 50)
    @NotBlank
    @Column(name = "surname")
    private String surname;

    /**
     * The user's date of birth.
     * This field is formatted as an ISO date.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * The user's nickname.
     * This field is required to be between 1 and 25 characters.
     */
    @Size(min = 1, max = 25)
    @NotBlank
    @Column(name = "nickname")
    private String nickname;

    /**
     * The user's email address.
     * This field must be a valid email format and must be unique.
     */
    @Email
    @Column(name = "email", unique = true)
    private String email;

    /**
     * The user's password.
     * This field stores the user's hashed password.
     */
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

    /**
     * The authorities (or roles) assigned to the user.
     * This field defines a many-to-many relationship with the {@link Authority} entity.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "\"APPUSER_AUTHORITY\"",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Authority> authoritiesList = new HashSet<>();

    /**
     * The user's puzzle scores.
     * This field defines a one-to-many relationship with the {@link UserPuzzleScore} entity.
     */
    @OneToMany(mappedBy = "user")
    private Set<UserPuzzleScore> puzzleScores;

    /**
     * The user's puzzle ratings.
     * This field defines a one-to-many relationship with the {@link UserPuzzleRating} entity.
     */
    @OneToMany(mappedBy = "user")
    private Set<UserPuzzleRating> puzzleRatings;

    /**
     * The user's comments.
     * This field defines a one-to-many relationship with the {@link Comment} entity.
     */
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

//    @OneToMany(mappedBy = "user")
//    private Set<SecureToken> tokens;

    @Column(name = "is_verified")
    private boolean accountVerified;

    /**
     * Return the authorities granted to the user.
     * This method converts the user's authorities to {@link GrantedAuthority} objects used by Spring Security.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritiesList.stream()
                .map(x -> new SimpleGrantedAuthority(x.getName().name()))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the username used for authentication.
     * This implementation returns the user's email address.
     *
     * @return the user's email address
     */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return accountVerified;
    }
}