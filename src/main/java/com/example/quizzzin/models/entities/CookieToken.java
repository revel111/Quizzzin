package com.example.quizzzin.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

/**
 * The CookieToken class represents the persistent login tokens used for remembering users.
 * This class is mapped to the "persistent_logins" table in the database.
 * It includes fields for storing the username, series identifier, token, and last used date.
 */
@Entity
@Table(name = "persistent_logins")
public class CookieToken {

    /**
     * The unique series identifier for this token.
     * This field is not nullable and serves as the primary key.
     */
    @Id
    @Column(name = "series", nullable = false)
    private String series;

    /**
     * The username of the user to whom this token belongs.
     * This field is not nullable.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * The token value.
     * This field is not nullable.
     */
    @Column(name = "token", nullable = false)
    private String token;

    /**
     * The date and time when this token was last used.
     * This field is not nullable.
     */
    @Column(name = "last_used", nullable = false)
    private Date lastUsed;
}