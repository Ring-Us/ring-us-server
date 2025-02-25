package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.common.Education;

public record EducationRequest(
        String schoolName,
        String major
) {
    public Education toEntity() {
        return new Education(
                schoolName,
                major
        );
    }
}