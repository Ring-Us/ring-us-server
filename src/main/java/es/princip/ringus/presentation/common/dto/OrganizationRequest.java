package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Organization;

public record OrganizationRequest(
       String name,
       String role,
       int experience
) {
    public Organization toEntity() {
        return new Organization(name, role, experience);
    }
}