package es.princip.ringus.domain.serviceTerm;

import es.princip.ringus.domain.base.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ServiceTermAgreement extends BaseTimeEntity {
    private final ServiceTerm tag;
    private final boolean agreed;
    private final LocalDateTime agreedAt;
}