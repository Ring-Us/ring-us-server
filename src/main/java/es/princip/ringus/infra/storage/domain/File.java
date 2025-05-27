package es.princip.ringus.infra.storage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class File {

    @Column(nullable = false)
    private String fileName; // 파일 이름 (S3 키)

    @Column(nullable = false)
    private String filePath; // S3 경로

    @Column
    private Integer fileSize; // 파일 크기 MB 단위

    protected File(String fileName, String filePath, Integer fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public File(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
