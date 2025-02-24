package es.princip.ringus.presentation.common.dto;

public record OrganizationRequest(
       String name,
       String role,
       int experience
) {
}