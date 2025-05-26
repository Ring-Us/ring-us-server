package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements ErrorCode {

    SESSION_EXPIRED(HttpStatus.UNAUTHORIZED, "세션이 거부됨"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버를 찾을 수 없음"),
    MEMBER_TYPE_DIFFERENT(HttpStatus.BAD_REQUEST, "다른 멤버 타입"),
    INVAILD_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 유효하지 않음"),
    DUPLICATE_EXISTING_PASSWORD(HttpStatus.BAD_REQUEST, "이미 사용 중인 비밀번호입니다.");

    MemberErrorCode(HttpStatus status, String message) {
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