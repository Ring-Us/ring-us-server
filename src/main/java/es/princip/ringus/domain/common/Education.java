package es.princip.ringus.domain.common;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 학력
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education {
    // 학교명 (필수)
    private String schoolName;
    // 전공명 (선택)
    private String major;
}