package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.common.Education;
import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.ProfileImage;
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
        final Long memberId
    ) {
        this.nickname = nickname;
        this.education = education;
        this.introduction = introduction;
        this.memberId = memberId;
    }

    /**
     * 프로필 이미지 업데이트
     */
    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}