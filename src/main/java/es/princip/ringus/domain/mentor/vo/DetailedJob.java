package es.princip.ringus.domain.mentor.vo;

import java.util.Arrays;

public enum DetailedJob {
    // Marketing
    BRAND_MARKETING("BRAND_MARKETING", "브랜드 마케팅", JobCategory.MARKETING),
    PERFORMANCE_MARKETING("PERFORMANCE_MARKETING", "퍼포먼스 마케팅", JobCategory.MARKETING),
    DIGITAL_SOCIAL_MARKETING("DIGITAL_SOCIAL_MARKETING", "디지털/소셜 마케팅", JobCategory.MARKETING),
    GROWTH_MARKETING("GROWTH_MARKETING", "그로스 마케팅", JobCategory.MARKETING),
    PR("PR", "PR", JobCategory.MARKETING),
    AE("AE", "AE", JobCategory.MARKETING),
    CONTENT_MARKETING("CONTENT_MARKETING", "콘텐츠 마케팅", JobCategory.MARKETING),
    CREATIVE_DIRECTING("CREATIVE_DIRECTING", "크리에이티브 디렉팅", JobCategory.MARKETING),
    COPYWRITER("COPYWRITER", "카피라이터", JobCategory.MARKETING),
    MEDIA_PLANNER("MEDIA_PLANNER", "미디어 플래너", JobCategory.MARKETING),
    BROADCAST_PD("BROADCAST_PD", "방송PD/영상PD", JobCategory.MARKETING),
    OTHER_MARKETING("OTHER_MARKETING", "기타", JobCategory.MARKETING),

    // Service Planning
    SERVICE_PLANNING("SERVICE_PLANNING", "서비스기획", JobCategory.SERVICE_PLANNING),
    PM_PO("PM_PO", "PM/PO", JobCategory.SERVICE_PLANNING),
    STRATEGY_PLANNING("STRATEGY_PLANNING", "전략 기획", JobCategory.SERVICE_PLANNING),
    OPERATION_PLANNING("OPERATION_PLANNING", "운영/기획", JobCategory.SERVICE_PLANNING),
    BUSINESS_DEVELOPMENT("BUSINESS_DEVELOPMENT", "사업 개발", JobCategory.SERVICE_PLANNING),
    CX_MANAGER("CX_MANAGER", "CX 매니저", JobCategory.SERVICE_PLANNING),
    STARTUP("STARTUP", "창업", JobCategory.SERVICE_PLANNING),
    OTHER_SERVICE_PLANNING("OTHER_SERVICE_PLANNING", "기타", JobCategory.SERVICE_PLANNING),

    // Design
    UX_UI_DESIGN("UX_UI_DESIGN", "UX/UI 디자인", JobCategory.DESIGN),
    GRAPHIC_DESIGN("GRAPHIC_DESIGN", "그래픽 디자인", JobCategory.DESIGN),
    PRODUCT_DESIGN("PRODUCT_DESIGN", "상품 디자인", JobCategory.DESIGN),
    BRAND_DESIGN("BRAND_DESIGN", "브랜드 디자인", JobCategory.DESIGN),
    WEB_DESIGN("WEB_DESIGN", "웹 디자인", JobCategory.DESIGN),
    ART_DIRECTOR("ART_DIRECTOR", "아트 디렉터", JobCategory.DESIGN),
    OTHER_DESIGN("OTHER_DESIGN", "기타", JobCategory.DESIGN),

    // Development
    FRONTEND("FRONTEND", "프론트엔드", JobCategory.DEVELOPMENT),
    BACKEND("BACKEND", "백엔드", JobCategory.DEVELOPMENT),
    FULLSTACK("FULLSTACK", "풀스택 개발자", JobCategory.DEVELOPMENT),
    IOS_ANDROID("IOS_ANDROID", "iOS/Android 개발자", JobCategory.DEVELOPMENT),
    DEVOPS("DEVOPS", "DevOps 엔지니어", JobCategory.DEVELOPMENT),
    CLOUD("CLOUD", "클라우드 엔지니어", JobCategory.DEVELOPMENT),
    SYSTEM_NETWORK("SYSTEM_NETWORK", "시스템/네트워크 엔지니어", JobCategory.DEVELOPMENT),
    SECURITY("SECURITY", "보안 엔지니어", JobCategory.DEVELOPMENT),
    OTHER_DEVELOPMENT("OTHER_DEVELOPMENT", "기타", JobCategory.DEVELOPMENT),

    // Graduate School
    DOMESTIC_GRADUATE_SCHOOL("DOMESTIC_GRADUATE_SCHOOL", "국내 대학원", JobCategory.GRADUATE_SCHOOL),
    OVERSEAS_GRADUATE_SCHOOL("OVERSEAS_GRADUATE_SCHOOL", "해외 대학원", JobCategory.GRADUATE_SCHOOL),
    OTHER_GRADUATE_SCHOOL("OTHER_GRADUATE_SCHOOL", "기타", JobCategory.GRADUATE_SCHOOL),

    // HR Support
    HR_PLANNING("HR_PLANNING", "인사기획", JobCategory.HR_SUPPORT),
    RECRUITMENT("RECRUITMENT", "채용담당", JobCategory.HR_SUPPORT),
    TALENT_DEVELOPMENT("TALENT_DEVELOPMENT", "인재육성/교육담당", JobCategory.HR_SUPPORT),
    ORGANIZATION_CULTURE("ORGANIZATION_CULTURE", "조직문화담당", JobCategory.HR_SUPPORT),
    LABOR("LABOR", "노무담당", JobCategory.HR_SUPPORT),
    GENERAL_AFFAIRS("GENERAL_AFFAIRS", "총무/경영지원", JobCategory.HR_SUPPORT),
    HR_OPERATION("HR_OPERATION", "인사운영", JobCategory.HR_SUPPORT),
    RECRUITER("RECRUITER", "리크루터", JobCategory.HR_SUPPORT),
    OTHER_HR_SUPPORT("OTHER_HR_SUPPORT", "기타", JobCategory.HR_SUPPORT),

    // Sales Customer
    B2B_SALES("B2B_SALES", "기업영업(B2B)", JobCategory.SALES_CUSTOMER),
    B2C_SALES("B2C_SALES", "개인영업(B2C)", JobCategory.SALES_CUSTOMER),
    OVERSEAS_SALES("OVERSEAS_SALES", "해외영업", JobCategory.SALES_CUSTOMER),
    TECHNICAL_SALES("TECHNICAL_SALES", "기술영업", JobCategory.SALES_CUSTOMER),
    SOLUTION_CONSULTANT("SOLUTION_CONSULTANT", "솔루션 컨설턴트", JobCategory.SALES_CUSTOMER),
    KAM("KAM", "주요고객관리(KAM)", JobCategory.SALES_CUSTOMER),
    SALES_SUPPORT("SALES_SUPPORT", "영업관리/지원", JobCategory.SALES_CUSTOMER),
    CSM_CX("CSM_CX", "CSM/CX", JobCategory.SALES_CUSTOMER),
    OTHER_SALES_CUSTOMER("OTHER_SALES_CUSTOMER", "기타", JobCategory.SALES_CUSTOMER),

    // Finance Consulting VC
    CONSULTANT("CONSULTANT", "컨설턴트", JobCategory.FINANCE_CONSULTING_VC),
    VC_INVESTMENT("VC_INVESTMENT", "VC/투자", JobCategory.FINANCE_CONSULTING_VC),
    IB_PE_ALTERNATIVE_INVESTMENT("IB_PE_ALTERNATIVE_INVESTMENT", "IB/PE/대체투자", JobCategory.FINANCE_CONSULTING_VC),
    ANALYST("ANALYST", "애널리스트", JobCategory.FINANCE_CONSULTING_VC),
    ACCOUNTING_FINANCE("ACCOUNTING_FINANCE", "회계/재무", JobCategory.FINANCE_CONSULTING_VC),
    OTHER_FINANCE_CONSULTING_VC("OTHER_FINANCE_CONSULTING_VC", "기타", JobCategory.FINANCE_CONSULTING_VC),

    // Data
    DATA_SCIENTIST("DATA_SCIENTIST", "데이터 사이언티스트", JobCategory.DATA),
    DATA_ENGINEER("DATA_ENGINEER", "데이터 엔지니어", JobCategory.DATA),
    DATA_ANALYST("DATA_ANALYST", "데이터 애널리스트", JobCategory.DATA),
    BI_ENGINEER("BI_ENGINEER", "BI 엔지니어", JobCategory.DATA),
    MACHINE_LEARNING_ENGINEER("MACHINE_LEARNING_ENGINEER", "머신러닝 엔지니어", JobCategory.DATA),
    DATA_ARCHITECT("DATA_ARCHITECT", "데이터 아키텍트", JobCategory.DATA),
    RESEARCH_ANALYST("RESEARCH_ANALYST", "리서치 애널리스트", JobCategory.DATA),
    OTHER_DATA("OTHER_DATA", "기타", JobCategory.DATA),

    // Medical
    CLINICAL_DOCTOR("CLINICAL_DOCTOR", "임상의사", JobCategory.MEDICAL),
    CLINICAL_RESEARCHER("CLINICAL_RESEARCHER", "임상연구원", JobCategory.MEDICAL),
    MEDICAL_DEVICE_RND("MEDICAL_DEVICE_RND", "의료기기 연구개발", JobCategory.MEDICAL),
    PHARMACEUTICAL_RESEARCHER("PHARMACEUTICAL_RESEARCHER", "제약회사 연구원", JobCategory.MEDICAL),
    BIO_RESEARCHER("BIO_RESEARCHER", "바이오 연구원", JobCategory.MEDICAL),
    OTHER_MEDICAL("OTHER_MEDICAL", "기타", JobCategory.MEDICAL),

    // Legal
    LAWYER("LAWYER", "변호사", JobCategory.LEGAL),
    LEGAL_COUNSEL("LEGAL_COUNSEL", "법무담당", JobCategory.LEGAL),
    PATENT("PATENT", "특허담당", JobCategory.LEGAL),
    COMPLIANCE("COMPLIANCE", "준법감시인(컴플라이언스)", JobCategory.LEGAL),
    LAW_FIRM_STAFF("LAW_FIRM_STAFF", "법무법인 사무직", JobCategory.LEGAL),
    LEGAL_ADVISOR("LEGAL_ADVISOR", "법률자문", JobCategory.LEGAL),
    PATENT_ENGINEER("PATENT_ENGINEER", "특허엔지니어", JobCategory.LEGAL),
    OTHER_LEGAL("OTHER_LEGAL", "기타", JobCategory.LEGAL);

    private final String code;
    private final String name;
    private final JobCategory category;

    DetailedJob(String code, String name, JobCategory category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public JobCategory getCategory() {
        return category;
    }

    public static DetailedJob from(String name) {
        return Arrays.stream(values())
                .filter(e -> e.code.equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(name + "매핑되는 카테고리가 없습니다: "));
    }
}