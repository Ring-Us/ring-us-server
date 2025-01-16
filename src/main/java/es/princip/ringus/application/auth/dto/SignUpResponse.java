package es.princip.ringus.application.auth.dto;

public record SignUpResponse(
        int status,
        String message,
        Object data
) {
}
