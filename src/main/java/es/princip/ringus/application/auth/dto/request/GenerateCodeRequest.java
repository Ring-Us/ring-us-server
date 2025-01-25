package es.princip.ringus.application.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record GenerateCodeRequest(
        @Email @NotBlank
        String email
) { }