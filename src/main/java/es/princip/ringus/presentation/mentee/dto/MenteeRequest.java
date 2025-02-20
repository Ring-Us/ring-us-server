package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.domain.mentee.EducationLevelType;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.common.Education;
import es.princip.ringus.presentation.common.dto.EducationRequest;
import jakarta.validation.constraints.NotBlank;

public record MenteeRequest(
        @NotBlank String email,
        @NotBlank String nickname,
        EducationRequest education,
        @NotBlank String introduction
) {
        public  Mentee toEntity() {
                return Mentee.builder()
                        .nickname(nickname)
                        .education(new Education(education.schoolName(), education().major()))
                        .introduction(introduction)
                        .build();
        }
}
