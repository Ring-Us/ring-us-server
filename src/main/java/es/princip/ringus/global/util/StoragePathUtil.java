package es.princip.ringus.global.util;

import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.infra.storage.domain.CertificateType;

public class StoragePathUtil {

    private StoragePathUtil() {} // 유틸리티 클래스이므로 인스턴스화 방지

    /**
     * 증명서 폴더 경로 생성
     */
    public static String buildCertificateFolderPath(CertificateType certificateType, Boolean isMentor) {
        String roleFolder = isMentor ? "mentor" : "mentee";
        return String.format("certificates/%s/%s", roleFolder, certificateType.name().toLowerCase());
    }

    /**
     * 프로필 이미지 폴더 경로 생성
     */
    public static String buildProfileFolderPath(MemberType userType) {
        return String.format("images/profile/%s", userType.name().toLowerCase());
    }
}
