package com.example.quizzzin.models.other;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@RequiredArgsConstructor
@NoArgsConstructor
public class CookieToken {
    @NonNull
    @Column(name = "username")
    private String username;
    @Id
    @NonNull
    @Column(name = "series")
    private String series;
    @NonNull
    @Column(name = "token")
    private String token;
    @NonNull
    @Column(name = "last_used")
    private Date lastUsed;
}