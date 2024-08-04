package com.example.quizzzin.enums;

/**
 * The {@code AuthorityType} enum defines the different types of authorities or roles that can be assigned
 * to users within the application.
 * <p>
 * Each constant in this enum represents a specific level of access or control within the application,
 * which helps in managing permissions and user roles.
 * </p>
 * <p>
 * The roles are ordered from least to most privileged:
 * <ul>
 *     <li>{@link #USER} - A standard user with basic access rights. Typically, users with this authority can perform
 *         general tasks but have limited administrative or management capabilities.</li>
 *     <li>{@link #MODERATOR} - A user with elevated privileges compared to a standard user. Moderators have
 *         additional responsibilities, such as overseeing content, managing user interactions, and handling
 *         reports or disputes.</li>
 *     <li>{@link #ADMIN} - The highest level of authority. Administrators have full control over the application,
 *         including managing users, configuring system settings, and performing any administrative tasks.</li>
 * </ul>
 * </p>
 */
public enum AuthorityType {
    USER,
    MODERATOR,
    ADMIN
}