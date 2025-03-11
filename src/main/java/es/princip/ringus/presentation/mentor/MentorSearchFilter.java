package es.princip.ringus.presentation.mentor;

public record MentorSearchFilter(
        boolean bookmarked,
        boolean commissioned
) {
}