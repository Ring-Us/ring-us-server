package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.mentoring.MentoringTopic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateMentoringRequest(
        @NotNull Long mentorId,
        MentoringTopic topic,
        @Size(min = 1, max = 5) List<MentoringTime> applyTimes,
        @NotBlank @Size(max = 500) String mentoringMessage
) {
}
