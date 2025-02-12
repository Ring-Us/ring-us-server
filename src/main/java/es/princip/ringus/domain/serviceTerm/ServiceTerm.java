package es.princip.ringus.domain.serviceTerm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "service_term")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceTerm {

    @Id
    @Column(name = "service_term_tag")
    private String tag;

    @Column(name = "required")
    private boolean required;

    @Builder
    public ServiceTerm(final String tag , final boolean required) {
        this.required = required;
        this.tag = tag;
    }

    public static ServiceTerm of(final String tag, boolean required) {
        return ServiceTerm.builder()
            .tag(tag)
            .required(required)
            .build();
    }
}
