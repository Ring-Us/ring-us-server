package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Portfolio;

public record PortfolioResponse(
        String url,
        String description,
        Integer fileSize
) {
    public static PortfolioResponse from(final Portfolio portfolio) {
        return new PortfolioResponse(portfolio.getFilePath(), portfolio.getFileName(), portfolio.getFileSize());
    }
}
