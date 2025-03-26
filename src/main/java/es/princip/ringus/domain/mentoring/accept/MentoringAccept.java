package es.princip.ringus.domain.mentoring.accept;

import es.princip.ringus.domain.base.BaseTimeEntity;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.mentoring.converter.MentoringTimeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "mentoring_accept")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentoringAccept extends BaseTimeEntity {

    @Id
    @Column(name = "mentoring_accept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = MentoringTimeConverter.class)
    @Column(length = 200, nullable = false)
    private MentoringTime mentoringTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private Mentee mentee;

    @Builder
    public MentoringAccept(final MentoringTime mentoringTime, final Mentor mentor, final Mentee mentee) {
        this.mentoringTime = mentoringTime;
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
