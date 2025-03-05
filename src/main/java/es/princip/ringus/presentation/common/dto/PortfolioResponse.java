package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Portfolio;

public record PortfolioResponse(
        String url,
        String description
) {
    public static PortfolioResponse of(final Portfolio portfolio) {
        return new PortfolioResponse(portfolio.getUrl(), portfolio.getDescription());
    }
}
