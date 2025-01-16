package es.princip.ringus.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public static Member of(String email, String password, PasswordEncoder passwordEncoder) {
        return new Member(
                email,
                passwordEncoder.encode(password)
        );
    }

    private Member(String email, String password) {
        this.email = email;
        this.password = password;
        this.memberType = MemberType.UNDETERMINED;
    }
}
