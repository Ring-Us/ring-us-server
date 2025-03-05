CREATE TABLE bookmark (
    bookmark_id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(6),
    mentee_id BIGINT NOT NULL,
    mentor_id BIGINT NOT NULL,
    updated_at DATETIME(6),
    PRIMARY KEY (bookmark_id)
);

CREATE TABLE file_member (
    is_verified BIT,
    created_at DATETIME(6),
    file_member_id BIGINT NOT NULL AUTO_INCREMENT,
    member_id BIGINT,
    updated_at DATETIME(6),
    file_path VARCHAR(255),
    PRIMARY KEY (file_member_id)
);

CREATE TABLE member (
    is_file_verified BIT NOT NULL,
    is_profile_registered BIT NOT NULL,
    is_university_verified BIT NOT NULL,
    created_at DATETIME(6),
    member_id BIGINT NOT NULL AUTO_INCREMENT,
    updated_at DATETIME(6),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    member_type ENUM ('ADMIN', 'MENTEE', 'MENTOR'),
    PRIMARY KEY (member_id)
);

CREATE TABLE mentee (
    member_id BIGINT,
    mentee_id BIGINT NOT NULL AUTO_INCREMENT,
    introduction VARCHAR(500),
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    major VARCHAR(255),
    nickname VARCHAR(255),
    school_name VARCHAR(255),
    PRIMARY KEY (mentee_id)
);

CREATE TABLE mentor (
    end_time TIME(6),
    experience INTEGER,
    start_time TIME(6),
    member_id BIGINT,
    mentor_id BIGINT NOT NULL AUTO_INCREMENT,
    introduction_title VARCHAR(15),
    message VARCHAR(50),
    introduction_content VARCHAR(300),
    description VARCHAR(255),
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    major VARCHAR(255),
    name VARCHAR(255),
    nickname VARCHAR(255),
    school_name VARCHAR(255),
    url VARCHAR(255),
    days VARCHAR(255),
    detailed_job ENUM (
        'ACCOUNTING_FINANCE', 'AE', 'ANALYST', 'ART_DIRECTOR', 'B2B_SALES', 'B2C_SALES', 'BACKEND', 'BIO_RESEARCHER',
        'BI_ENGINEER', 'BRAND_DESIGN', 'BRAND_MARKETING', 'BROADCAST_PD', 'BUSINESS_DEVELOPMENT', 'CLINICAL_DOCTOR',
        'CLINICAL_RESEARCHER', 'CLOUD', 'COMPLIANCE', 'CONSULTANT', 'CONTENT_MARKETING', 'COPYWRITER',
        'CREATIVE_DIRECTING', 'CSM_CX', 'CX_MANAGER', 'DATA_ANALYST', 'DATA_ARCHITECT', 'DATA_ENGINEER', 'DATA_SCIENTIST',
        'DEVOPS', 'DIGITAL_SOCIAL_MARKETING', 'DOMESTIC_GRADUATE_SCHOOL', 'FRONTEND', 'FULLSTACK', 'GENERAL_AFFAIRS',
        'GRAPHIC_DESIGN', 'GROWTH_MARKETING', 'HR_OPERATION', 'HR_PLANNING', 'IB_PE_ALTERNATIVE_INVESTMENT',
        'IOS_ANDROID', 'KAM', 'LABOR', 'LAWYER', 'LAW_FIRM_STAFF', 'LEGAL_ADVISOR', 'LEGAL_COUNSEL',
        'MACHINE_LEARNING_ENGINEER', 'MEDIA_PLANNER', 'MEDICAL_DEVICE_RND', 'OPERATION_PLANNING', 'ORGANIZATION_CULTURE',
        'OTHER_DATA', 'OTHER_DESIGN', 'OTHER_DEVELOPMENT', 'OTHER_FINANCE_CONSULTING_VC', 'OTHER_GRADUATE_SCHOOL',
        'OTHER_HR_SUPPORT', 'OTHER_LEGAL', 'OTHER_MARKETING', 'OTHER_MEDICAL', 'OTHER_SALES_CUSTOMER',
        'OTHER_SERVICE_PLANNING', 'OVERSEAS_GRADUATE_SCHOOL', 'OVERSEAS_SALES', 'PATENT', 'PATENT_ENGINEER',
        'PERFORMANCE_MARKETING', 'PHARMACEUTICAL_RESEARCHER', 'PM_PO', 'PR', 'PRODUCT_DESIGN', 'RECRUITER',
        'RECRUITMENT', 'RESEARCH_ANALYST', 'SALES_SUPPORT', 'SECURITY', 'SERVICE_PLANNING', 'SOLUTION_CONSULTANT',
        'STARTUP', 'STRATEGY_PLANNING', 'SYSTEM_NETWORK', 'TALENT_DEVELOPMENT', 'TECHNICAL_SALES', 'UX_UI_DESIGN',
        'VC_INVESTMENT', 'WEB_DESIGN'
        ),
    job_category ENUM ('DATA', 'DESIGN', 'DEVELOPMENT', 'FINANCE_CONSULTING_VC', 'GRADUATE_SCHOOL', 'HR_SUPPORT', 'LEGAL', 'MARKETING', 'MEDICAL', 'SALES_CUSTOMER', 'SERVICE_PLANNING'),
    PRIMARY KEY (mentor_id)
);

CREATE TABLE mentor_hashtags (
    mentor_id BIGINT NOT NULL,
    value VARCHAR(255)
);

CREATE TABLE mentor_mentoring_fields (
    mentor_id BIGINT NOT NULL,
    mentoring_field ENUM ('INTERVIEW_PREPARATION', 'JOB_PREPARATION', 'PORTFOLIO', 'PRACTICAL_SKILLS')
);

CREATE TABLE service_term (
    required BIT,
    service_term_tag VARCHAR(255) NOT NULL,
    PRIMARY KEY (service_term_tag)
);

CREATE TABLE service_term_agreement (
    agreed BIT,
    agreed_at DATETIME(6),
    created_at DATETIME(6),
    member_id BIGINT NOT NULL,
    updated_at DATETIME(6),
    tag VARCHAR(255)
);

-- 제약조건 추가
ALTER TABLE member ADD CONSTRAINT UNIQUE (email);
ALTER TABLE mentee ADD CONSTRAINT UNIQUE (nickname);

ALTER TABLE bookmark ADD CONSTRAINT FOREIGN KEY (mentee_id) REFERENCES mentee (mentee_id);
ALTER TABLE bookmark ADD CONSTRAINT FOREIGN KEY (mentor_id) REFERENCES mentor (mentor_id);
ALTER TABLE mentor_hashtags ADD FOREIGN KEY (mentor_id) REFERENCES mentor (mentor_id);
ALTER TABLE mentor_mentoring_fields ADD FOREIGN KEY (mentor_id) REFERENCES mentor (mentor_id);
ALTER TABLE service_term_agreement ADD FOREIGN KEY (member_id) REFERENCES member (member_id);