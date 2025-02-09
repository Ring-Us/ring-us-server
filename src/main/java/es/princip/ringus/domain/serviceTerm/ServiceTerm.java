package es.princip.ringus.domain.serviceTerm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Table(name = "service_term")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceTerm {

    @Id
    @Column(name = "service_term_tag")
    private String tag;

    @Builder
    public ServiceTerm(final String tag) {
        this.tag = tag;
    }

    public static ServiceTerm of(final String tag) {
        return ServiceTerm.builder()
            .tag(tag)
            .build();
    }
}
