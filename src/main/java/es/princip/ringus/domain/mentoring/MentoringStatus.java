package es.princip.ringus.domain.mentoring;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "멘토링 상태")
public enum MentoringStatus {
    @Schema(description = "대기중, WAITING")
    WAITING,
    @Schema(description = "수락됨, ACCEPTED")
    ACCEPTED,
    @Schema(description = "거절됨, REJECTED")
    REJECTED
}
