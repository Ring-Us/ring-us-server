package es.princip.ringus.infra.storage.application;

import es.princip.ringus.domain.exception.FileErrorCode;
import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.infra.storage.domain.FileMember;
import es.princip.ringus.infra.storage.domain.FileMemberRepository;
import es.princip.ringus.infra.storage.dto.FilePreviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StorageFileService {
    private final S3Service s3Service;
    private final FileMemberRepository fileMemberRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FilePreviewResponse getFile(Long fileMemberId) {
        FileMember fileMember = fileMemberRepository.findById(fileMemberId)
                .orElseThrow(() -> new CustomRuntimeException(FileErrorCode.FILE_NOT_FOUND));

        Member member = getMember(fileMember.getMemberId());

        String presignedUrl = getPresignedUrl(fileMember.getFilePath());

        return FilePreviewResponse.of(presignedUrl, fileMember, member);
    }

    public List<FilePreviewResponse> getFiles() {
        List<FileMember> fileMembers = fileMemberRepository.findAll();
        return fileMembers.stream()
                .map(fileMember -> {
                    Member member = getMember(fileMember.getMemberId());
                    String presignedUrl = getPresignedUrl(fileMember.getFilePath());
                    return FilePreviewResponse.of(presignedUrl, fileMember, member);
                })
                .collect(Collectors.toList());
    }

    private String getPresignedUrl(String filePath) {
        return s3Service.generatePresignedUrl(filePath, Duration.ofMinutes(10));
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
