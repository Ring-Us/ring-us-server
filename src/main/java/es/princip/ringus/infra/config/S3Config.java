package es.princip.ringus.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class S3Config {
    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.access-key:}")
    private String accessKey; // 액세스 키 (없으면 빈 값)

    @Value("${aws.s3.secret-key:}")
    private String secretKey; // 시크릿 키 (없으면 빈 값)

    @Bean
    public S3Client s3Client() {
        // 로컬 환경에서 액세스 키와 시크릿 키가 설정되어 있으면 StaticCredentialsProvider 사용
        if (!accessKey.isEmpty() && !secretKey.isEmpty()) {
            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
            return S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                    .build();
        }

        // EC2 환경에서는 DefaultCredentialsProvider 사용 (IAM 역할)
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
