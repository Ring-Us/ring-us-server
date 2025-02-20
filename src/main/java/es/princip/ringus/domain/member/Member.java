package es.princip.ringus.domain.member;

import es.princip.ringus.domain.base.BaseTimeEntity;
import es.princip.ringus.domain.serviceTerm.ServiceTermAgreement;
import es.princip.ringus.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "service_term_agreement", joinColumns = @JoinColumn(name = "member_id"))
    private Set<ServiceTermAgreement> serviceTerms;

    private boolean isFileVerified;

    private boolean isProfileRegistered;

    private boolean isUniversityVerified;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    public static Member of(
        final String email,
        final String password,
        final PasswordEncoder passwordEncoder,
        final String memberType,
        final Set<ServiceTermAgreement> serviceTerms,
        final boolean isUniversityVerified
    ) {
        return new Member(
            MemberType.valueOf(memberType),
            passwordEncoder.encode(password),
            email,
            serviceTerms,
            isUniversityVerified
        );
    }

    @Builder
    public Member(
        final MemberType memberType,
        final String password,
        final String email,
        final Set<ServiceTermAgreement> serviceTerms,
        final Boolean isUniversityVerified
    ) {
        this.memberType = memberType;
        this.password = password;
        this.email = email;
        this.serviceTerms = serviceTerms;
        this.isFileVerified = false;
        this.isProfileRegistered = false;
        this.isUniversityVerified = isUniversityVerified;
    }

    public void verifyFile() {
        this.isFileVerified = true;
    }

    public void registerProfile() {
        this.isProfileRegistered = true;
    }

    public void confirmUniversity() {
        this.isUniversityVerified = true;
    }
}
