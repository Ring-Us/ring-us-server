package es.princip.ringus.application.mentee.service;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.exception.MenteeErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentee.dto.MyMenteeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyMenteeService {
    private final MemberRepository memberRepository;
    private final MenteeRepository menteeRepository;

    public MyMenteeResponse getDetailBy(Long mentorId) {
        Mentee mentee = menteeRepository.findByMemberId(mentorId)
                .orElseThrow(() -> new CustomRuntimeException(MenteeErrorCode.MENTEE_NOT_FOUND));

        Member member = memberRepository.findById(mentee.getMemberId())
                .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MyMenteeResponse.from(member,mentee);
    }
}
