package es.princip.ringus.domain.mentor;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.domain.mentor.vo.Hashtag;
import es.princip.ringus.domain.mentor.vo.MentoringField;
import es.princip.ringus.domain.mentor.vo.Organization;
import es.princip.ringus.domain.mentor.vo.Portfolio;
import es.princip.ringus.domain.mentor.vo.Timezone;
import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "mentor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentor {

    @Id
    @Column(name = "mentor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 닉네임
    @Column(name = "nickname")
    private String nickname;

    // 학력
    @Embedded
    private Education education;

    // 조직
    @Embedded
    private Organization organization;

    // 자기소개
    @Column(name = "introduction", length = 500)
    private String introduction;

    // 선호 시간대
    @Embedded
    private Timezone timezone;

    // 멘토링 분야
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mentor_mentoring_fields", joinColumns = @JoinColumn(name = "mentor_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "mentoring_field")
    private Set<MentoringField> mentoringField;

    // 해시태그
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mentor_hashtags", joinColumns = @JoinColumn(name = "mentor_id"))
    private List<Hashtag> hashtags;

    // 멘티에게 전하는 말
    @Column(name = "message", length = 50)
    private String message;

    // 포트폴리오
    @Embedded
    private Portfolio portfolio;

    @Embedded
    private ProfileImage profileImage;

    @Column(name = "member_id")
    private Long memberId;

    @Builder
    public Mentor(
        final String nickname,
        final Education education,
        final Organization organization,
        final String introduction,
        final Timezone timezone,
        final Set<MentoringField> mentoringField,
        final List<Hashtag> hashtags,
        final String message,
        final Portfolio portfolio,
        final Long memberId
    ) {
        this.nickname = nickname;
        this.education = education;
        this.organization = organization;
        this.introduction = introduction;
        this.timezone = timezone;
        this.mentoringField = mentoringField;
        this.hashtags = hashtags;
        this.message = message;
        this.portfolio = portfolio;
        this.memberId = memberId;
    }

    /**
     * 프로필 이미지 업데이트
     */
    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}