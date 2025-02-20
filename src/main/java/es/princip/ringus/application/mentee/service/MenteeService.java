package es.princip.ringus.application.mentee.service;

import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
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
    public Long register(MenteeRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.WRONG_EMAIL));
        Mentee mentee = request.toEntity();
        return  menteeRepository.save(mentee).getId();
    }
}