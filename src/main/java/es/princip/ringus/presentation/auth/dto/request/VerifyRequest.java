package es.princip.ringus.presentation.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VerifyRequest(
        @NotBlank @Email
        String email,
        @NotBlank
        String code
) { }