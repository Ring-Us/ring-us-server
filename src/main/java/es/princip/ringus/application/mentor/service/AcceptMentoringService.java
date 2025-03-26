package es.princip.ringus.application.mentor.service;

import es.princip.ringus.domain.exception.MenteeErrorCode;
import es.princip.ringus.domain.exception.MentorErrorCode;
import es.princip.ringus.domain.exception.MentoringErrorCode;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringRepository;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.mentoring.accept.MentoringAccept;
import es.princip.ringus.domain.mentoring.accept.MentoringAcceptRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentoring.dto.AcceptMentoringRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptMentoringService {
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final MentoringRepository mentoringRepository;
    private final MentoringAcceptRepository mentoringAcceptRepository;

    private boolean isRequestedTimeInMentoringTime(Mentoring mentoring, AcceptMentoringRequest request) {
        return mentoring.getApplyTimes().stream()
                .anyMatch(time -> MentoringTime.equals(time, request.mentoringTime()));
    }

    public Long accept(AcceptMentoringRequest request, Long memberId) {
        Mentor mentor = mentorRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_NOT_FOUND));
        Mentee mentee = menteeRepository.findById(request.menteeId())
                .orElseThrow(() -> new CustomRuntimeException(MenteeErrorCode.MENTEE_NOT_FOUND));

        Mentoring mentoring = mentoringRepository.findByMenteeIdAndMentorId(mentee.getId(), mentor.getId())
                .orElseThrow(() -> new CustomRuntimeException(MentoringErrorCode.MENTORING_NOT_FOUND));

        if (mentoring.isNotWaiting()) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_STATUS_NOT_WAITING);
        }
        if (!isRequestedTimeInMentoringTime(mentoring, request)) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_NOT_MATCH);
        }

        MentoringAccept accept = MentoringAccept.builder()
                .mentee(mentee)
                .mentor(mentor)
                .mentoringTime(request.mentoringTime())
                .build();
        mentoring.accept();

        return mentoringAcceptRepository.save(accept).getId();
    }
}
