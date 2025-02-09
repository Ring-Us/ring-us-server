package es.princip.ringus.presentation.serviceTerm.dto;

import jakarta.validation.constraints.NotNull;

public record ServiceTermRequest (
    @NotNull String tag
) {
}