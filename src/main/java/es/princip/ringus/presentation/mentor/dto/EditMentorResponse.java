package es.princip.ringus.presentation.mentor.dto;

public record EditMentorResponse(
        Long mentorId
) {
    public static EditMentorResponse from(final Long mentorId) {
        return new EditMentorResponse(mentorId);
    }
}