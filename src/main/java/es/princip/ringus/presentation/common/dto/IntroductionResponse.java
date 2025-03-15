package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Introduction;

public record IntroductionResponse(
        String title,
        String content
) {
    public static IntroductionResponse from(final Introduction introduction) {
        return new IntroductionResponse(introduction.getTitle(), introduction.getContent());
    }
}
