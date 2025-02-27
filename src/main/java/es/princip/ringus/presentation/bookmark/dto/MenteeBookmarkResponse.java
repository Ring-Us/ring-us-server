package es.princip.ringus.presentation.bookmark.dto;

public record MenteeBookmarkResponse(
        Long mentorId,
        String mentorName
) {
    public static MenteeBookmarkResponse from(Long mentorId, String mentorName){
        return new MenteeBookmarkResponse(mentorId, mentorName);
    }
}