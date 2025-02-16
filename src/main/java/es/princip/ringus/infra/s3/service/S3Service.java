package es.princip.ringus.infra.s3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    /**
     * S3에 파일 업로드
     * @param file 업로드할 파일
     * @param folderPath 저장될 S3 폴더 경로
     * @return 업로드된 파일의 S3 키 (Key)
     */
    public String uploadFile(MultipartFile file, String folderPath) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String s3Key = folderPath + "/" + fileName;

        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(s3Key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }

        return s3Key; // 업로드된 파일의 S3 키 반환
    }
}
