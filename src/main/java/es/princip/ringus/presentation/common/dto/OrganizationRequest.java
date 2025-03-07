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
        return new Organization(name, JobCategory.valueOf(jobCategory), DetailedJob.valueOf(detailedJob), experience);
    }
}