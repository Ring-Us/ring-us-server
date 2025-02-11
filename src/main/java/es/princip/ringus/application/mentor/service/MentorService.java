package es.princip.ringus.application.mentor.service;

import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.user.dto.MentorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentorService {

    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    @Transactional
    public void createMentor(MentorRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.WRONG_EMAIL));

        if (mentorRepository.existsByMember(member)) {
            throw new CustomRuntimeException(SignUpErrorCode.ALREADY_REGISTERED_AS_MENTOR);
        }
        if (menteeRepository.existsByMember(member)) {
            throw new CustomRuntimeException(SignUpErrorCode.ALREADY_REGISTERED_AS_MENTEE);
        }

        Mentor mentor = Mentor.of(member, request.nickname(), request.introduction(), request.company(), request.job(), request.experience());

        mentorRepository.save(mentor);
    }

}
