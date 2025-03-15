package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.common.Education;

public record EducationResponse(
        String schoolName,
        String major
) {
    public static EducationResponse from(final Education education) {
        return new EducationResponse(
                education.getSchoolName(),
                education.getMajor()
        );
    }
}
