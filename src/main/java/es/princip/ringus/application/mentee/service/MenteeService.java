package es.princip.ringus.application.mentee.service;

import es.princip.ringus.domain.exception.MenteeErrorCode;
import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentee.dto.EditMenteeRequest;
import es.princip.ringus.presentation.mentee.dto.MenteeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenteeService {

    private final MemberRepository memberRepository;
    private final MenteeRepository menteeRepository;

    @Transactional
    public Long register(Long memberId, MenteeRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.NOT_FOUND_MEMBER));

        if (menteeRepository.existsByMemberId(memberId)) {
            throw new CustomRuntimeException(MenteeErrorCode.ALREADY_REGISTERED_MENTEE);
        }

        if (member.isNotMentee()) {
            throw new CustomRuntimeException(MenteeErrorCode.MEMBER_TYPE_NOT_MENTEE);
        }

        member.registerProfile();

        Mentee mentee = request.toEntity(member.getId());
        return  menteeRepository.save(mentee).getId();
    }

    @Transactional
    public Long edit(Long memberId, EditMenteeRequest request) {
        Mentee mentee = menteeRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MenteeErrorCode.MENTEE_PROFILE_NOT_FOUND));

        mentee.edit(request);
        return mentee.getId();
    }
}