package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Introduction;

public record IntroductionRequest(
        String title,
        String content
) {
    public Introduction toEntity() {
        return new Introduction(title, content);
    }
}