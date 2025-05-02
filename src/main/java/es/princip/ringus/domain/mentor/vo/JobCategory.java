package es.princip.ringus.domain.mentor.vo;

import java.util.Arrays;

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
    private final String name;

    JobCategory(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static JobCategory from(String name) {
        return Arrays.stream(values())
                .filter(e -> e.code.equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(name + "매핑되는 카테고리가 없습니다: "));
    }
}