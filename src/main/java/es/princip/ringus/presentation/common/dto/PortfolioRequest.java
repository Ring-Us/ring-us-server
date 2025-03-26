package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Portfolio;

public record PortfolioRequest(
        String url,
        String description
) {
    public Portfolio toEntity() {
        return new Portfolio(url, description);
    }
}
