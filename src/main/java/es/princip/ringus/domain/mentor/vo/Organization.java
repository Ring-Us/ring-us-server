package es.princip.ringus.domain.mentor.vo;

import jakarta.persistence.Embeddable;
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
    // 직무 (필수)
    private String role;
    // 경력 (필수)
    private int experience;
}
