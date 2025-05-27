package es.princip.ringus.application.mentor.service;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.exception.MentorErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.domain.mentoring.MentoringRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentor.dto.MyMentorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyMentorService {
    private  final MentorRepository mentorRepository;
    private final MemberRepository memberRepository;
    private  final MentoringRepository mentoringRepository;

    public MyMentorResponse getDetailBy(Long memberId) {
        Mentor mentor = mentorRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_PROFILE_NOT_FOUND));

        Member member = memberRepository.findById(mentor.getMemberId())
                .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MyMentorResponse.from(member, mentor, mentoringRepository.findMentoringCountBy(mentor.getId()));
    }
}
