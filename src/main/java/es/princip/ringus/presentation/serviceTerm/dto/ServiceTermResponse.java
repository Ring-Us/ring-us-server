package es.princip.ringus.presentation.serviceTerm.dto;

import es.princip.ringus.domain.serviceTerm.ServiceTerm;
import jakarta.validation.constraints.NotNull;

public record ServiceTermResponse(
    @NotNull String tag
) {
    public static ServiceTermResponse from(final ServiceTerm serviceTerm) {
        return new ServiceTermResponse(
            serviceTerm.getTag()
        );
    }
}