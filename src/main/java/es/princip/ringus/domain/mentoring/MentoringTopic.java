package es.princip.ringus.domain.mentoring;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;

@Getter
@Schema(description = "멘토링 주제")
public enum MentoringTopic {
    JOB_PREPARATION("JOB_PREPARATION", "취업 준비"),
    INTERVIEW_PREPARATION("INTERVIEW_PREPARATION", "면접 대비"),
    INDUSTRY_TRENDS("INDUSTRY_TRENDS", "업계 동향"),
    CAREER_PATH("CAREER_PATH", "커리어 고민");

    private final String code;
    private final String kor;

    MentoringTopic(String code, String kor) {
        this.code = code;
        this.kor = kor;
    }

    public static MentoringTopic from(String kor) {
        return Arrays.stream(values())
            .filter(e -> e.kor.equals(kor))
            .findFirst()
            .orElseThrow(
                    () -> new IllegalArgumentException("매핑되는 MentoringTopic이 없습니다: " + kor)
            );
    }
}