package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.mentoring.MentoringTopic;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateMentoringRequest(
        @NotNull @Schema(description = "멘토 ID", required = true, example = "1")
        Long mentorId,
        @Schema(description = "멘토링 주제", required = true, example = "STUDY")
        MentoringTopic topic,
        @Size(min = 1, max = 5)
        @Schema(description = "멘토링 시간, 최대 5개", required = true, example = "[{\"startTime\": \"2025-03-10T10:00:00\", \"endTime\": \"2025-03-10T11:00:00\"}]")
        List<MentoringTime> applyTimes,
        @NotBlank @Size(max = 500) @Schema(description = "멘토링 메시지", example = "멘토링 신청합니다.")
        String mentoringMessage
) {
}
