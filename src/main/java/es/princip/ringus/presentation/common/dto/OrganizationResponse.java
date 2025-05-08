package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Organization;

public record OrganizationResponse(
        String name,
        String jobCategory,
        String detailedJob,
        int experience
) {
    public static OrganizationResponse from(final Organization organization) {
        return new OrganizationResponse(
                organization.getName(),
                organization.getJobCategory().getKor(),
                organization.getDetailedJob().getKor(),
                organization.getExperience()
        );
    }
}
