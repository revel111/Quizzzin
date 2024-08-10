package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"SECURE_TOKEN\"")
@NoArgsConstructor
@RequiredArgsConstructor
public class SecureToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", unique = true)
    @NonNull
    private String token;

    @NonNull
    @Column(name = "date_of_expiration", updatable = false)
    @Basic(optional = false)
    private LocalDateTime dateOfExpiration;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}