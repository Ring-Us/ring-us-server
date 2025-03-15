package es.princip.ringus.infra.storage.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "증명서 타입")
public enum CertificateType {
    @Schema(description = "재학증명서")
    ENROLLMENT, // 재학증명서
    @Schema(description = "졸업증명서")
    GRADUATION, // 졸업증명서
    @Schema(description = "재직증명서")
    EMPLOYMENT, // 재직증명서
}
