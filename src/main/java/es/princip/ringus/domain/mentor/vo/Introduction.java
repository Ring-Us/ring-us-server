package es.princip.ringus.domain.mentor.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 자기소개
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Introduction {
    // 제목
    @Column(name="introduction_title", length = 15)
    private String title;
    // 내용
    @Column(name="introduction_content", length = 300)
    private String content;
}