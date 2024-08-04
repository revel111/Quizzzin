package com.example.quizzzin.models.entities;

import com.example.quizzzin.enums.AuthorityType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Authority class represents an authority (or permission) within the application.
 * It defines the attributes for authorities that can be assigned to roles.
 * This class is mapped to the "AUTHORITY" table in the database.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "\"AUTHORITY\"")
public class Authority {

    /**
     * The unique identifier for the authority.
     * This field is auto-generated and serves as the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the authority.
     * This field uses an enumerated type (AuthorityType) and is mapped to the "name" column in the "AUTHORITY" table.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private AuthorityType name;
}