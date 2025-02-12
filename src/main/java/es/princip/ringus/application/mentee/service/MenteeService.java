package es.princip.ringus.application.mentee.service;

import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.user.dto.MenteeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenteeService {

    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    @Transactional
    public void createMentee(MenteeRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.WRONG_EMAIL));

        if (mentorRepository.existsByMember(member)) {
            throw new CustomRuntimeException(SignUpErrorCode.ALREADY_REGISTERED_AS_MENTOR);
        }
        if (menteeRepository.existsByMember(member)) {
            throw new CustomRuntimeException(SignUpErrorCode.ALREADY_REGISTERED_AS_MENTEE);
        }

        Mentee mentee = Mentee.of(member, request.nickname(), request.introduction(), request.major(), request.educationLevelType());

        menteeRepository.save(mentee);
    }

}
