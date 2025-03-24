package es.princip.ringus.domain.exception;

import es.princip.ringus.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum FileErrorCode  implements ErrorCode {
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND,"파일을 찾을 수 없음"),
    FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST,"파일 업로드 실패"),
    FILE_DELETE_FAILED(HttpStatus.BAD_REQUEST,"파일 삭제 실패"),
    FILE_DOWNLOAD_FAILED(HttpStatus.BAD_REQUEST,"파일 다운로드 실패"),
    FILE_NOT_ALLOWED(HttpStatus.BAD_REQUEST,"허용되지 않는 파일 형식"),
    FILE_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST,"파일 크기 초과");

    FileErrorCode( HttpStatus status,String message) {
        this.status = status;
        this.message = message;
    }

    private final String message;
    private final HttpStatus status;

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
