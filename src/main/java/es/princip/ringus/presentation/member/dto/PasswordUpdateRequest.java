package es.princip.ringus.presentation.member.dto;

public record PasswordUpdateRequest(
    String email,
    String newPassword
) { }
