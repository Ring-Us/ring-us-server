package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.user.User;
import es.princip.ringus.domain.user.UserType;
import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Mentee extends User {

    private String major;

    @Enumerated(EnumType.STRING)
    private EducationLevelType levelType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    private ProfileImage profileImage;

    @Builder
    public Mentee(Member member, String nickname, String introduction, String major, EducationLevelType levelType) {
        super(nickname, introduction, UserType.MENTEE, member);
        this.major = major;
        this.levelType = levelType;
    }

    public static Mentee of(Member member, String nickname, String introduction, String major, EducationLevelType levelType) {
        return Mentee.builder()
                .member(member)
                .nickname(nickname)
                .introduction(introduction)
                .major(major)
                .levelType(levelType)
                .build();
    }

    public void updateCertificate(Certificate certificate) {
        this.certificate = certificate;
    }


    /**
     * 프로필 이미지 업데이트
     */
    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
