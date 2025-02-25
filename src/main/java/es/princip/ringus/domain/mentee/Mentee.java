package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.presentation.mentee.dto.EditMenteeRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "mentee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentee {

    @Id
    @Column(name = "mentee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 닉네임
    @Column(name = "nickname", unique = true)
    private String nickname;

    // 학력
    @Embedded
    private Education education;

    // 자기소개
    @Column(name = "introduction", length = 500)
    private String introduction;

    @Embedded
    private ProfileImage profileImage;

    @Column(name = "member_id")
    private Long memberId;

    @Builder
    public Mentee(
        final String nickname,
        final Education education,
        final String introduction,
        final ProfileImage profileImage,
        final Long memberId
    ) {
        this.nickname = nickname;
        this.education = education;
        this.introduction = introduction;
        this.profileImage = profileImage;
        this.memberId = memberId;
    }

    public void edit(final EditMenteeRequest request) {
        this.nickname = request.nickname();
        this.education = request.education().toEntity();
        this.introduction = request.introduction();
        this.profileImage = request.image().toEntity();
    }
}