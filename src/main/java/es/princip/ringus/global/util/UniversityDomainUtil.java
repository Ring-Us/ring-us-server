package es.princip.ringus.global.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class UniversityDomainUtil {

    private static String filePath;
    private static final Set<String> universityDomains = new HashSet<>();

    public UniversityDomainUtil(@Value("${university.file.path}") String filePath) {
        UniversityDomainUtil.filePath = filePath;
    }

    @PostConstruct
    private void init() {
        loadUniversityDomains();
    }

    /**
     * 대학 도메인 목록을 파일에서 로드하여 universityDomains에 저장
     */
    private static void loadUniversityDomains() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }

            String[] parts = content.toString().trim().split(" ");
            for (int i = 1; i < parts.length; i += 2) {
                universityDomains.add(parts[i]);
            }

        } catch (IOException e) {
            throw new RuntimeException("대학 도메인을 읽어오는데 실패함", e);
        }
    }

    /**
     * 이메일이 대학 도메인인지 확인
     * @param email 검사할 이메일 주소
     * @return 대학 도메인에 해당하면 true, 아니면 false
     */
    public static boolean isUniversityEmail(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        return universityDomains.contains(domain);
    }
}
