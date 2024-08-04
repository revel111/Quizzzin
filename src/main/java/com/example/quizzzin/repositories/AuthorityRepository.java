package com.example.quizzzin.repositories;

import com.example.quizzzin.enums.AuthorityType;
import com.example.quizzzin.models.entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The RoleRepository interface provides CRUD operations for Authority entities.
 * It extends the {@link CrudRepository} interface provided by Spring Data JPA.
 * <p>
 * Dependencies:
 * - {@link CrudRepository}: Spring Data interface for generic CRUD operations.
 */
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    /**
     * Finds an Authority entity by its name.
     *
     * @param name The name of the authority to find, represented as a {@link AuthorityType}.
     * @return The {@link Authority} entity matching the specified name, or null if not found.
     */
    Authority findByName(AuthorityType name);
}