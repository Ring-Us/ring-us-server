package es.princip.ringus.domain.mentor;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.user.User;
import es.princip.ringus.domain.user.UserType;
import es.princip.ringus.infra.storage.domain.Certificate;
import es.princip.ringus.infra.storage.domain.CertificateType;
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
public class Mentor extends User {

    private String company;

    private String job;

    private int experience;

    // 졸업증명서 (멘토용)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "graduation_certificate_id")
    private Certificate graduationCertificate;

    // 재직증명서 (멘토용)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_certificate_id")
    private Certificate employmentCertificate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    private ProfileImage profileImage;


    @Builder
    public Mentor(Member member, String nickname, String introduction, String company, String job, int experience) {
        super(nickname, introduction, UserType.MENTOR, member);
        this.company = company;
        this.job = job;
        this.experience = experience;
    }

    public static Mentor of(Member member, String nickname, String introduction, String company, String job, int experience) {
        return Mentor.builder()
                .member(member)
                .nickname(nickname)
                .introduction(introduction)
                .company(company)
                .job(job)
                .experience(experience)
                .build();
    }

    /**
     * 증명서 업데이트 메서드
     * 전달된 증명서의 타입에 따라 해당 증명서를 업데이트.
     */
    public void updateCertificate(Certificate certificate) {
        if (certificate.getCertificateType() == CertificateType.GRADUATION) {
            this.graduationCertificate = certificate;
        } else if (certificate.getCertificateType() == CertificateType.EMPLOYMENT) {
            this.employmentCertificate = certificate;
        } else {
            throw new IllegalArgumentException("멘토용 증명서는 졸업증명서 또는 재직증명서여야 합니다.");
        }
    }

    /**
     * 프로필 이미지 업데이트
     */
    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
