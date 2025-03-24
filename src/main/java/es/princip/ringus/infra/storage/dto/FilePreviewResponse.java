package es.princip.ringus.infra.storage.dto;

import es.princip.ringus.domain.member.Member;

import es.princip.ringus.infra.storage.domain.FileMember;
import es.princip.ringus.presentation.member.dto.MemberResponse;

public record FilePreviewResponse(
    String filePath,
    MemberResponse memberResponse,
    Boolean isVerified
) {
    public static FilePreviewResponse of(String presignedUrl,FileMember fileMember, Member member) {
        return new FilePreviewResponse(
            presignedUrl,
            MemberResponse.of(member),
            fileMember.getIsVerified()
        );
    }
}
