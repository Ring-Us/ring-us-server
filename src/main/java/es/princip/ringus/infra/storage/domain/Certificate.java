package es.princip.ringus.infra.storage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certificate extends File {

    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;

    @Builder
    public Certificate(String fileName, String filePath, CertificateType certificateType) {
        super(fileName, filePath);
        this.certificateType = certificateType;
    }
}
