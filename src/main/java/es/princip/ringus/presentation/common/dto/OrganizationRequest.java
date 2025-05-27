package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.DetailedJob;
import es.princip.ringus.domain.mentor.vo.JobCategory;
import es.princip.ringus.domain.mentor.vo.Organization;

public record OrganizationRequest(
        String name,
        String jobCategory,
        String detailedJob,
        int experience
) {
    public Organization toEntity() {

        JobCategory jobCategoryKor   = JobCategory.from(jobCategory);
        DetailedJob detailedJobKor   = DetailedJob.from(detailedJob);

        if (!detailedJobKor.getCategory().getCode().equals(jobCategoryKor.getCode())) {
            throw new IllegalArgumentException(
                    "세부 직무의 직무 카테고리가 직무 카테고리와 일치하지 않습니다.");
        }
        return new Organization(
                name,
                jobCategoryKor,
                detailedJobKor,
                experience
        );
    }
}