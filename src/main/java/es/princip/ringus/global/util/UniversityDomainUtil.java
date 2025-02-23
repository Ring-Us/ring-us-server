package es.princip.ringus.global.util;

import lombok.Getter;

@Getter
public enum UniversityDomainUtil {
    KNU("knu.ac.kr");

    private final String domain;

    UniversityDomainUtil(String domain) {
        this.domain = domain;
    }

    /**
     * 주어진 이메일이 대학 도메인에 속하는지 확인
     */
    public static boolean isUniversityVerified(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        for (UniversityDomainUtil university : values()) {
            if (university.getDomain().equals(domain)) {
                return true;
            }
        }
        return false;
    }
}
