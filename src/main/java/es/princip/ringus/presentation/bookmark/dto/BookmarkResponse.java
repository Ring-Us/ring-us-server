package es.princip.ringus.presentation.bookmark.dto;

public record BookmarkResponse(
        Long bookmarkId
) {
    public static BookmarkResponse from(Long bookmarkId) {
        return new BookmarkResponse(bookmarkId);
    }
}