package es.princip.ringus.domain.mentor;

import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.user.User;
import es.princip.ringus.domain.user.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Mentor extends User {

    private String company;

    private String job;

    private int experience;

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
}
