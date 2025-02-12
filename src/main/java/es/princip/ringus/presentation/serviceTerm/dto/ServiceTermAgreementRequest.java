package es.princip.ringus.presentation.serviceTerm.dto;

import jakarta.validation.constraints.NotNull;

public record ServiceTermAgreementRequest(
        @NotNull String tag,
        @NotNull boolean agreed
) {
}
