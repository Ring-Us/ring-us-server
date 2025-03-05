package es.princip.ringus.domain.mentor.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 조직
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organization {
    // 조직명 (필수)
    private String name;

    // 직무
    @Enumerated(EnumType.STRING)
    @Column(name = "job_category")
    private JobCategory jobCategory;

    // 세부 직무
    @Enumerated(EnumType.STRING)
    @Column(name = "detailed_job")
    private DetailedJob detailedJob;

    // 경력 (필수)
    private int experience;
}
