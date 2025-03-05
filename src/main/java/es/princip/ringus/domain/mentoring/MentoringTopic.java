package es.princip.ringus.domain.mentoring;

import lombok.Getter;

@Getter
public enum MentoringTopic {
    STUDY("학업 관련"),
    INDUSTRY_TRENDS("업계 동향"),
    INTERVIEW("면접 대비"),
    JOB_PREP("취업 준비");

    private final String description;

    MentoringTopic(String description) {
        this.description = description;
    }

}
