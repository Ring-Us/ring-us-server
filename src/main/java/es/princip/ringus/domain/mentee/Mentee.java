package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.user.User;
import es.princip.ringus.domain.user.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Mentee extends User {

    private String major;

    @Enumerated(EnumType.STRING)
    private EducationLevelType levelType;

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

}
