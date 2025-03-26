package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum BookmarkErrorCode implements ErrorCode {

    BOOKMARK_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 북마크된 멘토"),
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 북마크가 존재하지 않음");

    BookmarkErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public String code() {
        return this.name();
    }
}