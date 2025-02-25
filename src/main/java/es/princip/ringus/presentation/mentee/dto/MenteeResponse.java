package es.princip.ringus.presentation.mentee.dto;

public record MenteeResponse(
    Long menteeId
) {
    public static MenteeResponse from(final Long menteeId) {
        return new MenteeResponse(menteeId);
    }
}
