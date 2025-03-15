package es.princip.ringus.domain.mentee;

import es.princip.ringus.domain.bookmark.Bookmark;
import es.princip.ringus.domain.common.Education;
import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.infra.storage.domain.ProfileImage;
import es.princip.ringus.presentation.mentee.dto.EditMenteeRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    //북마크
    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL ,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mentoring> mentorings = new ArrayList<>();

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

    public void addBookmark(Bookmark bookmark){
        bookmarks.add(bookmark);
    }

    public void deleteBookmark(Bookmark bookmark){
        bookmarks.remove(bookmark);
    }

    public void addMentoring(Mentoring mentoring){
        mentorings.add(mentoring);
    }

    public void deleteMentoring(Mentoring mentoring){
        mentorings.remove(mentoring);
    }

}