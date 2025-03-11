package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MenteeErrorCode implements ErrorCode {
    MEMBER_TYPE_NOT_MENTEE(HttpStatus.BAD_REQUEST, "멘티로 등록된 회원이 아님"),
    MENTEE_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "멘티 프로필을 등록한 적이 없음"),
    ALREADY_REGISTERED_MENTEE(HttpStatus.BAD_REQUEST, "이미 멘티로 등록된 회원"),
    MENTEE_NOT_FOUND(HttpStatus.NOT_FOUND, "멘티 정보를 찾을 수 없음");

    MenteeErrorCode(HttpStatus status, String message) {
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