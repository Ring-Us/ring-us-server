package es.princip.ringus.domain.mentoring;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "멘토링 주제")
public enum MentoringTopic {
    @Schema(description = "학업 관련, STUDY")
    STUDY("학업 관련"),
    @Schema(description = "업계 동향, INDUSTRY_TRENDS")
    INDUSTRY_TRENDS("업계 동향"),
    @Schema(description = "면접대비, INTERVIEW")
    INTERVIEW("면접 대비"),
    @Schema(description = "취업 준비, JOB_PREP")
    JOB_PREP("취업 준비");

    private final String description;

    MentoringTopic(String description) {
        this.description = description;
    }

}
