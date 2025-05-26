package es.princip.ringus.application.mentoring;

import es.princip.ringus.application.notification.service.NotificationService;
import es.princip.ringus.domain.exception.MenteeErrorCode;
import es.princip.ringus.domain.exception.MentorErrorCode;
import es.princip.ringus.domain.exception.MentoringErrorCode;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringRepository;
import es.princip.ringus.domain.mentoring.MentoringStatus;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.sender.dto.MentoringRequestMessage;
import es.princip.ringus.presentation.mentoring.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentoringService {
    private final MentoringRepository mentoringRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    private final NotificationService notificationService;
    /**
     * 멘토링 신청 생성
     */
    @Transactional
    public MentoringResponse createMentoring(CreateMentoringRequest request, Long memberId) {
        Mentor mentor = mentorRepository.findById(request.mentorId())
                .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_NOT_FOUND));
        Mentee mentee = menteeRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MenteeErrorCode.MENTEE_NOT_FOUND));
        final Mentoring mentoring = Mentoring.of(
                MentoringStatus.WAITING,
                request.topic(),
                request.applyTimes(),
                request.mentoringMessage(),
                mentor,
                mentee
        );

        mentee.addMentoring(mentoring);
        mentor.addMentoring(mentoring);

        notificationService.notify(MentoringRequestMessage.from(mentee, mentor, mentoring));
        return MentoringResponse.from(mentoringRepository.save(mentoring));
    }

    /**
     * 멘토링 신청 취소
     */
    @Transactional
    public MentoringCancelResponse cancelMentoring(CancelMentoringRequest request, Long memberId) {
        Mentoring mentoring = mentoringRepository.findById(request.mentoringId())
                .orElseThrow(() -> new CustomRuntimeException(MentoringErrorCode.MENTORING_NOT_FOUND));

        if (!mentoring.getMentee().getMemberId().equals(memberId)) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_CANCEL_NOT_POSSIBLE);
        }

        mentoring.cancel();
        return MentoringCancelResponse.from(mentoring);
    }

    /**
     * 멘토링 신청 거절
     */
    @Transactional
    public MentoringRejectResponse rejectMentoring(RejectMentoringRequest request, Long memberId) {
        Mentoring mentoring = mentoringRepository.findById(request.mentoringId())
                .orElseThrow(() -> new CustomRuntimeException(MentoringErrorCode.MENTORING_NOT_FOUND));

        if (!mentoring.getMentor().getMemberId().equals(memberId)) {
            throw new CustomRuntimeException(MentorErrorCode.MENTOR_NOT_FOUND);
        }

        mentoring.reject();

        return MentoringRejectResponse.from(mentoring);
    }
}