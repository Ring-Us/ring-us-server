package es.princip.ringus.domain.user;

import es.princip.ringus.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@DiscriminatorColumn
public class User {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(unique = true)
    private String nickname;

    @Column(length = 500)
    private String introduction;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserInterest> userInterests = new ArrayList<>();

    public User(String nickname, String introduction, UserType userType, Member member) {
        this.member = member;
        this.nickname = nickname;
        this.introduction = introduction;
        this.userType = userType;
    }
}
