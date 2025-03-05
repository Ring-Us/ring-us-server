package es.princip.ringus.domain.apply;

import lombok.Getter;

@Getter
public enum ApplyTopic {
    STUDY("학업 관련"),
    INDUSTRY_TRENDS("업계 동향"),
    INTERVIEW("면접 대비"),
    JOB_PREP("취업 준비");

    private final String description;

    ApplyTopic(String description) {
        this.description = description;
    }

}
