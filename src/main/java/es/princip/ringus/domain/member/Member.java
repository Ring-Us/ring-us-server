package es.princip.ringus.domain.member;

import es.princip.ringus.domain.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public static Member of(
        final String email,
        final String password,
        final PasswordEncoder passwordEncoder,
        final String memberType
    ) {
        return new Member(
            MemberType.valueOf(memberType),
            passwordEncoder.encode(password),
            email
        );
    }

    @Builder
    public Member(
        final MemberType memberType,
        final String password,
        final String email
    ) {
        this.memberType = memberType;
        this.password = password;
        this.email = email;
    }
}
