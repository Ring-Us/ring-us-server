package es.princip.ringus.domain.serviceTerm;

import es.princip.ringus.domain.base.BaseTimeEntity;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
public class ServiceTermAgreement extends BaseTimeEntity {

    private String tag;
    private boolean agreed;
    private LocalDateTime agreedAt;

    public ServiceTermAgreement(String tag, boolean agreed) {
        this.tag = tag;
        this.agreed = agreed;
        this.agreedAt = LocalDateTime.now();
    }
}