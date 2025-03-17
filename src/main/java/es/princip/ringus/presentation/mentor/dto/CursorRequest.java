package es.princip.ringus.presentation.mentor.dto;

public record CursorRequest(
        boolean bookmarked,
        boolean commissioned,
        boolean suggested,
        Long cursor
) {
    public boolean isBookmarked(){
        return bookmarked();
    }
    public boolean isSuggested(){
        return suggested();
    }
}
