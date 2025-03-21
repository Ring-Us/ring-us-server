package es.princip.ringus.application.mentor.service;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.exception.MentorErrorCode;
import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.member.MemberType;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.domain.support.CursorResponse;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentor.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static es.princip.ringus.application.support.CursorParser.parse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentorService {

    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;

    @Transactional
    public Long register(Long memberId, MentorRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.NOT_FOUND_MEMBER));

        if (mentorRepository.existsByMemberId(memberId)) {
            throw new CustomRuntimeException(MentorErrorCode.ALREADY_REGISTERED_MENTOR);
        }

        if (member.isNotMentor()) {
            throw new CustomRuntimeException(MentorErrorCode.MEMBER_TYPE_NOT_MENTOR);
        }

        member.registerProfile();

        Mentor mentor = request.toEntity(memberId);
        return  mentorRepository.save(mentor).getId();
    }

    @Transactional
    public Long edit(Long memberId, EditMentorRequest request) {
        Mentor mentor = mentorRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_PROFILE_NOT_FOUND));
        mentor.edit(request);
        return mentor.getId();
    }

    public CursorResponse<MentorCardResponse> getMentorBy(CursorRequest request, Pageable pageable, Long memberId) {
        if(request.isBookmarked()) {
            Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));

            if (member.isNotMentee()) {
                throw new CustomRuntimeException(MemberErrorCode.MEMBER_TYPE_DIFFERENT);
            }
        }

        final Slice<MentorCardResponse> response = mentorRepository.findMentorBy(request, pageable, memberId);
        final  Long cursor = parse(request.cursor(), response);
        return CursorResponse.of(response, cursor);
    }

    public MentorDetailResponse getDetailBy(Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_PROFILE_NOT_FOUND));
        return MentorDetailResponse.from(mentor);
    }
}