package es.princip.ringus.presentation.mentee.dto;

public record EditMenteeResponse(
        Long menteeId
) {
    public static EditMenteeResponse from(final Long menteeId) {
        return new EditMenteeResponse(menteeId);
    }
}
