package es.princip.ringus.presentation.mentee.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.presentation.common.dto.EducationRequest;
import es.princip.ringus.presentation.common.dto.ProfileImageRequest;
import jakarta.validation.constraints.NotBlank;

public record MenteeRequest(
        @NotBlank String nickname,
        EducationRequest education,
        ProfileImageRequest image,
        @NotBlank String introduction
) {
    public  Mentee toEntity(Long memberId) {
        return Mentee.builder()
                .nickname(nickname)
                .education(education.toEntity())
                .profileImage(image.toEntity())
                .introduction(introduction)
                .memberId(memberId)
                .build();
    }
}
