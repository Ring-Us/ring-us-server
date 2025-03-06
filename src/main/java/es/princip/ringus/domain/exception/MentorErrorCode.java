package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MentorErrorCode implements ErrorCode  {
    MEMBER_TYPE_NOT_MENTOR(HttpStatus.BAD_REQUEST, "멘토가 아닌 회원입니다."),
    MENTOR_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "멘토 프로필을 등록한 적이 없음"),
    MENTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "멘토 정보를 찾을 수 없음"),
    ALREADY_REGISTERED_MENTOR(HttpStatus.BAD_REQUEST, "이미 멘토로 등록된 회원입니다.");

    MentorErrorCode(HttpStatus status, String message) {
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
