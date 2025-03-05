package es.princip.ringus.domain.mentoring;

import es.princip.ringus.domain.mentoring.converter.MentoringTimeConverter;
import es.princip.ringus.domain.base.BaseTimeEntity;
import es.princip.ringus.domain.exception.MentoringErrorCode;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "mentoring")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentoring extends BaseTimeEntity {

    @Id
    @Column(name = "mentoring_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MentoringStatus status;

    @Enumerated(EnumType.STRING)
    private MentoringTopic topic;

    @Convert(converter = MentoringTimeConverter.class)
    @Column(length = 500, nullable = false) // 500자 이내
    //@Column(columnDefinition = "TEXT") // 500자 초과 시
    private List<MentoringTime> applyTimes;

    @Column(length = 500, nullable = false)
    private String mentoringMessage; // 상담 메시지 추가 (최대 500자)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private Mentee mentee;

    @Builder
    public Mentoring(
        final MentoringStatus status,
        final MentoringTopic topic,
        final List<MentoringTime> applyTimes,
        final String mentoringMessage,
        final Mentor mentor,
        final Mentee mentee
    ) {
        if (applyTimes == null || applyTimes.isEmpty()) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_ERROR);
        }
        if (applyTimes.size() > 5) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_ERROR);
        }
        this.status = status;
        this.topic = topic;
        this.applyTimes = applyTimes;
        this.mentoringMessage = mentoringMessage;
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
