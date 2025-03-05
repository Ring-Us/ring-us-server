package es.princip.ringus.domain.mentor.vo;

public enum DetailedJob {
    // Marketing
    BRAND_MARKETING(JobCategory.MARKETING), // 브랜드 마케팅
    PERFORMANCE_MARKETING(JobCategory.MARKETING), // 퍼포먼스 마케팅
    DIGITAL_SOCIAL_MARKETING(JobCategory.MARKETING), // 디지털/소셜 마케팅
    GROWTH_MARKETING(JobCategory.MARKETING), // 그로스 마케팅
    PR(JobCategory.MARKETING), // PR
    AE(JobCategory.MARKETING), // AE
    CONTENT_MARKETING(JobCategory.MARKETING), // 콘텐츠 마케팅
    CREATIVE_DIRECTING(JobCategory.MARKETING), // 크리에이티브 디렉팅
    COPYWRITER(JobCategory.MARKETING), // 카피라이터
    MEDIA_PLANNER(JobCategory.MARKETING), // 미디어 플래너
    BROADCAST_PD(JobCategory.MARKETING), // 방송PD/영상PD
    OTHER_MARKETING(JobCategory.MARKETING), // 기타

    // Service Planning
    SERVICE_PLANNING(JobCategory.SERVICE_PLANNING), // 서비스기획
    PM_PO(JobCategory.SERVICE_PLANNING), // PM/PO
    STRATEGY_PLANNING(JobCategory.SERVICE_PLANNING), // 전략 기획
    OPERATION_PLANNING(JobCategory.SERVICE_PLANNING), // 운영/기획
    BUSINESS_DEVELOPMENT(JobCategory.SERVICE_PLANNING), // 사업 개발
    CX_MANAGER(JobCategory.SERVICE_PLANNING), // CX 매니저
    STARTUP(JobCategory.SERVICE_PLANNING), // 창업
    OTHER_SERVICE_PLANNING(JobCategory.SERVICE_PLANNING), // 기타

    // Design
    UX_UI_DESIGN(JobCategory.DESIGN), // UX/UI디자인
    GRAPHIC_DESIGN(JobCategory.DESIGN), // 그래픽 디자인
    PRODUCT_DESIGN(JobCategory.DESIGN), // 상품 디자인
    BRAND_DESIGN(JobCategory.DESIGN), // 브랜드 디자인
    WEB_DESIGN(JobCategory.DESIGN), // 웹 디자인
    ART_DIRECTOR(JobCategory.DESIGN), // 아트 디렉터
    OTHER_DESIGN(JobCategory.DESIGN), // 기타

    // Development
    FRONTEND(JobCategory.DEVELOPMENT), // 프론트엔드
    BACKEND(JobCategory.DEVELOPMENT), // 백엔드
    FULLSTACK(JobCategory.DEVELOPMENT), // 풀스택 개발자
    IOS_ANDROID(JobCategory.DEVELOPMENT), // iOS/Android 개발자
    DEVOPS(JobCategory.DEVELOPMENT), // DevOps 엔지니어
    CLOUD(JobCategory.DEVELOPMENT), // 클라우드 엔지니어
    SYSTEM_NETWORK(JobCategory.DEVELOPMENT), // 시스템/네트워크 엔지니어
    SECURITY(JobCategory.DEVELOPMENT), // 보안 엔지니어
    OTHER_DEVELOPMENT(JobCategory.DEVELOPMENT), // 기타

    // Graduate School
    DOMESTIC_GRADUATE_SCHOOL(JobCategory.GRADUATE_SCHOOL), // 국내 대학원
    OVERSEAS_GRADUATE_SCHOOL(JobCategory.GRADUATE_SCHOOL), // 해외 대학원
    OTHER_GRADUATE_SCHOOL(JobCategory.GRADUATE_SCHOOL), // 기타

    // HR Support
    HR_PLANNING(JobCategory.HR_SUPPORT), // 인사기획
    RECRUITMENT(JobCategory.HR_SUPPORT), // 채용담당
    TALENT_DEVELOPMENT(JobCategory.HR_SUPPORT), // 인재육성/교육담당
    ORGANIZATION_CULTURE(JobCategory.HR_SUPPORT), // 조직문화담당
    LABOR(JobCategory.HR_SUPPORT), // 노무담당
    GENERAL_AFFAIRS(JobCategory.HR_SUPPORT), // 총무/경영지원
    HR_OPERATION(JobCategory.HR_SUPPORT), // 인사운영
    RECRUITER(JobCategory.HR_SUPPORT), // 리크루터
    OTHER_HR_SUPPOReT(JobCategory.HR_SUPPORT), // 기타

    // Sales Customer
    B2B_SALES(JobCategory.SALES_CUSTOMER), // 기업영업(B2B)
    B2C_SALES(JobCategory.SALES_CUSTOMER), // 개인영업(B2C)
    OVERSEAS_SALES(JobCategory.SALES_CUSTOMER), // 해외영업
    TECHNICAL_SALES(JobCategory.SALES_CUSTOMER), // 기술영업
    SOLUTION_CONSULTANT(JobCategory.SALES_CUSTOMER), // 솔루션 컨설턴트
    KAM(JobCategory.SALES_CUSTOMER), // 주요고객관리(KAM)
    SALES_SUPPORT(JobCategory.SALES_CUSTOMER), // 영업관리/지원
    CSM_CX(JobCategory.SALES_CUSTOMER), // CSM/CX
    OTHER_SALES_CUSTOMER(JobCategory.SALES_CUSTOMER), // 기타

    // Finance Consulting VC
    CONSULTANT(JobCategory.FINANCE_CONSULTING_VC), // 컨설턴트
    VC_INVESTMENT(JobCategory.FINANCE_CONSULTING_VC), // VC/투자
    IB_PE_ALTERNATIVE_INVESTMENT(JobCategory.FINANCE_CONSULTING_VC), // IB/PE/대체투자
    ANALYST(JobCategory.FINANCE_CONSULTING_VC), // 에널리스트
    ACCOUNTING_FINANCE(JobCategory.FINANCE_CONSULTING_VC), // 회계/재무
    OTHER_FINANCE_CONSULTING_VC(JobCategory.FINANCE_CONSULTING_VC), // 기타

    // Data
    DATA_SCIENTIST(JobCategory.DATA), // 데이터 사이언티스트
    DATA_ENGINEER(JobCategory.DATA), // 데이터 엔지니어
    DATA_ANALYST(JobCategory.DATA), // 데이터 애널리스트
    BI_ENGINEER(JobCategory.DATA), // BI 엔지니어
    MACHINE_LEARNING_ENGINEER(JobCategory.DATA), // 머신러닝 엔지니어
    DATA_ARCHITECT(JobCategory.DATA), // 데이터 아키텍트
    RESEARCH_ANALYST(JobCategory.DATA), // 리서치 애널리스트
    OTHER_DATA(JobCategory.DATA), // 기타

    // Medical
    CLINICAL_DOCTOR(JobCategory.MEDICAL), // 임상의사
    CLINICAL_RESEARCHER(JobCategory.MEDICAL), // 임상연구원
    MEDICAL_DEVICE_RND(JobCategory.MEDICAL), // 의료기기 연구개발
    PHARMACEUTICAL_RESEARCHER(JobCategory.MEDICAL), // 제약회사 연구원
    BIO_RESEARCHER(JobCategory.MEDICAL), // 바이오 연구원
    OTHER_MEDICAL(JobCategory.MEDICAL), // 기타

    // Legal
    LAWYER(JobCategory.LEGAL), // 변호사
    LEGAL_COUNSEL(JobCategory.LEGAL), // 법무담당
    PATENT(JobCategory.LEGAL), // 특허담당
    COMPLIANCE(JobCategory.LEGAL), // 준법감시인(컴플라이언스)
    LAW_FIRM_STAFF(JobCategory.LEGAL), // 법무법인 사무직
    LEGAL_ADVISOR(JobCategory.LEGAL), // 법률자문
    PATENT_ENGINEER(JobCategory.LEGAL), // 특허엔지니어
    OTHER_LEGAL(JobCategory.LEGAL); // 기타

    private final JobCategory category;

    DetailedJob(JobCategory category) {
        this.category = category;
    }

    public JobCategory getCategory() {
        return category;
    }
}