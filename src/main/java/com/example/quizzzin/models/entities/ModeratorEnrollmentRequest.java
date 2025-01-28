package com.example.quizzzin.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "\"MODERATOR_ENROLLMENT_REQUEST\"")
public class ModeratorEnrollmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 50, max = 500)
    @NotBlank
    @NotEmpty
    private String text;

    private String workHours;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}