package es.princip.ringus.presentation.bookmark.dto;

import es.princip.ringus.domain.bookmark.Bookmark;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentor.Mentor;
import jakarta.validation.constraints.NotNull;

public record BookmarkRequest(
        @NotNull Long mentorId,
        @NotNull Long menteeId
) {
    public Bookmark toEntity(Mentor mentor, Mentee mentee){
        return Bookmark.builder()
                .mentor(mentor)
                .mentee(mentee)
                .build();
    }
}