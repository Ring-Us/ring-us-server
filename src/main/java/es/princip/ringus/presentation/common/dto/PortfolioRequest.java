package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Portfolio;

public record PortfolioRequest(
        String url,
        String description,
        Integer fileSize
) {
    public Portfolio toEntity() {
        return Portfolio.builder()
                .filePath(url)
                .fileName(description)
                .fileSize(fileSize)
                .build();
    }
}
