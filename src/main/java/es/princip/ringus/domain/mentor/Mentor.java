package es.princip.ringus.domain.mentor;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.domain.mentor.vo.*;
import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.presentation.mentor.dto.EditMentorRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Embedded
    private Introduction introduction;

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

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mentoring> mentorings = new ArrayList<>();

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
        final Introduction introduction,
        final Timezone timezone,
        final Set<MentoringField> mentoringField,
        final List<Hashtag> hashtags,
        final String message,
        final Portfolio portfolio,
        final ProfileImage profileImage,
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
        this.profileImage = profileImage;
        this.memberId = memberId;
    }

    public void edit(final EditMentorRequest request) {
        this.nickname = request.nickname();
        this.education = request.education().toEntity();
        this.organization = request.organization().toEntity();
        this.introduction = request.introduction().toEntity();
        this.timezone = request.timezone().toEntity();
        this.mentoringField = request.mentoringField().stream().map(MentoringField::valueOf).collect(Collectors.toSet());
        this.hashtags = request.hashtags().stream().map(Hashtag::new).toList();
        this.message = request.message();
        this.portfolio = request.portfolio().toEntity();
    }

    public void addMentoring(Mentoring mentoring){
        mentorings.add(mentoring);
    }

    public void deleteMentoring(Mentoring mentoring){
        mentorings.remove(mentoring);
    }
}