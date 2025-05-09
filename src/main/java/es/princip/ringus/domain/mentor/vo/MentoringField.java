package es.princip.ringus.domain.mentor.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MentoringField {
    JOB_PREPARATION("JOB_PREPARATION", "취업 준비"),
    INTERVIEW_PREPARATION("INTERVIEW_PREPARATION", "면접 대비"),
    INDUSTRY_TRENDS("INDUSTRY_TRENDS", "업계 동향"),
    CAREER_PATH("CAREER_PATH", "커리어 고민");

    private final String code;
    private final String kor;

    MentoringField(String code, String kor) {
        this.code = code;
        this.kor = kor;
    }

    public static MentoringField from(String kor) {
        return Arrays.stream(values())
                .filter(e -> e.kor.equals(kor))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("매핑되는 MentoringField가 없습니다: " + kor)
                );
    }
}