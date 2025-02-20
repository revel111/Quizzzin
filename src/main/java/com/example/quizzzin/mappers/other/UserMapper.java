package com.example.quizzzin.mappers.other;

import com.example.quizzzin.models.dto.other.RegisterUserDTO;
import com.example.quizzzin.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * The {@code UserMapper} interface defines mappings between the {@link RegisterUserDTO} data transfer object
 * and the {@link User} entity. It uses MapStruct to handle the conversion between these two types.
 * <p>
 * This interface provides a way to automatically map data between the DTO used for user registration and
 * the user entity that represents the user in the database.
 * </p>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    /**
     * Converts a {@link RegisterUserDTO} to a {@link User} entity.
     * <p>
     * This method maps the fields of {@code RegisterUserDTO} to the {@link User} entity. It includes mapping
     * for the user's date of birth, which is formatted as "yyyy-MM-dd". The password is also directly mapped.
     * </p>
     *
     * @param registerUserDTO The {@link RegisterUserDTO} to be converted.
     * @return A {@link User} entity with fields populated from the provided {@code RegisterUserDTO}.
     */
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authoritiesList", ignore = true)
    @Mapping(target = "puzzleScores", ignore = true)
    @Mapping(target = "puzzleRatings", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "accountVerified", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toUser(RegisterUserDTO registerUserDTO);
}