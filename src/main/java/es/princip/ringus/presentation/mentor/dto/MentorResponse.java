package es.princip.ringus.presentation.mentor.dto;

public record MentorResponse(
    Long mentorId
) {
    public static MentorResponse from(Long mentorId) {
        return new MentorResponse(mentorId);
    }
}
