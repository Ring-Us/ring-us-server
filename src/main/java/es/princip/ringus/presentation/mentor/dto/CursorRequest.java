package es.princip.ringus.presentation.mentor.dto;

public record CursorRequest(
        boolean bookmarked,
        boolean commissioned,
        Long cursor
) {
}
