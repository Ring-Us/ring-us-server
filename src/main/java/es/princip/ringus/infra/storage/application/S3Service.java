package es.princip.ringus.infra.storage.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    /**
     * S3에 파일 업로드
     * @param file       업로드할 파일
     * @param folderPath S3에 저장할 폴더 경로 (예: "profile-images/mentor", "certificates/mentee/ENROLLMENT" 등)
     * @return 업로드된 파일의 S3 URL
     */
    public String uploadFile(MultipartFile file, String folderPath) {
        String fileName = UUID.randomUUID() + "_" + sanitizeFileName(file.getOriginalFilename());
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
            throw new RuntimeException("파일 입력 스트림을 읽지 못했습니다.", e);
        } catch (S3Exception e) {
            throw new RuntimeException("S3에 파일 업로드에 실패했습니다.", e);
        }

        return getFileUrl(s3Key);
    }

    /**
     * S3에 저장된 파일의 URL 반환
     */
    private String getFileUrl(String s3Key) {
        return "https://" + bucketName + ".s3.amazonaws.com/" + s3Key;
    }

    /**
     * 파일 이름을 URL 인코딩
     */
    private String sanitizeFileName(String originalFilename) {
        try {
            return URLEncoder.encode(originalFilename, StandardCharsets.UTF_8.toString())
                    .replaceAll("\\+", "%20");
        } catch (Exception e) {
            throw new RuntimeException("파일 이름 인코딩에 실패했습니다.", e);
        }
    }
}
