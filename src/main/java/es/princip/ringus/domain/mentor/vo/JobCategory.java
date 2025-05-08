package es.princip.ringus.domain.mentor.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum JobCategory {
    MARKETING("MARKETING", "마케팅/광고홍보/미디어"),
    SERVICE_PLANNING("SERVICE_PLANNING", "서비스 기획/사업/운영"),
    DESIGN("DESIGN", "디자인"),
    DEVELOPMENT("DEVELOPMENT", "개발"),
    GRADUATE_SCHOOL("GRADUATE_SCHOOL", "대학원"),
    HR_SUPPORT("HR_SUPPORT", "인사/채용/경영지원"),
    SALES_CUSTOMER("SALES_CUSTOMER", "영업/고객"),
    FINANCE_CONSULTING_VC("FINANCE_CONSULTING_VC", "금융/컨설팅/VC/재무"),
    DATA("DATA", "데이터"),
    MEDICAL("MEDICAL", "의료"),
    LEGAL("LEGAL", "법률");

    private final String code;
    private final String kor;

    JobCategory(String code, String kor) {
        this.code = code;
        this.kor = kor;
    }

    public static JobCategory from(String kor) {
        return Arrays.stream(values())
                .filter(e -> e.kor.equals(kor))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("매핑되는 JobCategory가 없습니다: " + kor));
    }
}