package es.princip.ringus.application.mentor.service;

import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentor.dto.MentorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentorService {

    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;

    @Transactional
    public Long register(MentorRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.WRONG_EMAIL));
        Mentor mentor = request.toEntity(member.getId());
        return  mentorRepository.save(mentor).getId();
    }
}