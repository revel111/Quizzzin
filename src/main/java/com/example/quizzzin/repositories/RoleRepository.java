package com.example.quizzzin.repositories;

import com.example.quizzzin.enums.RoleType;
import com.example.quizzzin.models.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(RoleType name);
}