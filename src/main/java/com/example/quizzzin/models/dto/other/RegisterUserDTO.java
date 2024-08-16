package com.example.quizzzin.models.dto.other;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * The {@code RegisterUserDTO} class is a data transfer object (DTO) used for user registration.
 * It contains the information required to register a new user in the system, including personal details
 * and login credentials. This DTO is used to transfer user registration data from the presentation
 * layer to the service layer of the application.
 * <p>
 * This DTO ensures that the data provided for user registration meets specific validation criteria.
 * </p>
 */
@Data
public class RegisterUserDTO {

    /**
     * The first name of the user.
     * Must be between 2 and 50 characters in length and cannot be blank.
     */
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    /**
     * The surname of the user.
     * Must be between 2 and 50 characters in length and cannot be blank.
     */
    @Size(min = 2, max = 50)
    @NotBlank
    private String surname;

    /**
     * The date of birth of the user.
     * This field uses ISO date format and is optional.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    /**
     * The nickname or username of the user.
     * Must be between 1 and 25 characters in length and cannot be blank.
     */
    @Size(min = 1, max = 25)
    @NotBlank
    private String nickname;

    /**
     * The email address of the user.
     * Must be a valid email format.
     */
    @Email
    private String email;

    /**
     * The password for the user account.
     * Password validation is commented out but typically includes requirements such as
     * - At least one digit
     * - At least one lowercase letter
     * - At least one uppercase letter
     * - At least one special character
     * - No whitespace
     * - Minimum length of 8 characters
     */
    // @Pattern.List({
    //     @Pattern(regexp = ".*[0-9].*"),
    //     @Pattern(regexp = ".*[a-z].*"),
    //     @Pattern(regexp = ".*[A-Z].*"),
    //     @Pattern(regexp = ".*[@#$%^&+=!].*"),
    //     @Pattern(regexp = "\\S+"),
    //     @Pattern(regexp = ".{8,}")
    // })
    private String password;

    // @Pattern.List({
    //     @Pattern(regexp = ".*[0-9].*"),
    //     @Pattern(regexp = ".*[a-z].*"),
    //     @Pattern(regexp = ".*[A-Z].*"),
    //     @Pattern(regexp = ".*[@#$%^&+=!].*"),
    //     @Pattern(regexp = "\\S+"),
    //     @Pattern(regexp = ".{8,}")
    // })
    private String confirmationPassword;
}