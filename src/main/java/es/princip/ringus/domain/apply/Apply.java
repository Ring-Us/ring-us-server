package es.princip.ringus.domain.apply;

import es.princip.ringus.domain.apply.converter.ApplyTimeConverter;
import es.princip.ringus.domain.base.BaseTimeEntity;
import es.princip.ringus.domain.exception.ApplyErrorCode;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "apply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply extends BaseTimeEntity {

    @Id
    @Column(name = "apply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplyStatus status;

    @Enumerated(EnumType.STRING)
    private ApplyTopic topic;

    @Convert(converter = ApplyTimeConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<ApplyTime> applyTimes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private Mentee mentee;

    @Builder
    public Apply(
        final ApplyStatus status,
        final ApplyTopic topic,
        final List<ApplyTime> applyTimes,
        final Mentor mentor,
        final Mentee mentee
    ) {
        if (applyTimes == null || applyTimes.isEmpty()) {
            throw new CustomRuntimeException(ApplyErrorCode.APPLY_TIME_ERROR);
        }
        if (applyTimes.size() > 5) {
            throw new CustomRuntimeException(ApplyErrorCode.APPLY_TIME_ERROR);
        }
        this.status = status;
        this.topic = topic;
        this.applyTimes = applyTimes;
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
