package es.princip.ringus.application.mentoring;

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
import es.princip.ringus.presentation.mentoring.dto.CreateMentoringRequest;
import es.princip.ringus.presentation.mentoring.dto.EditMentoringTimeRequest;
import es.princip.ringus.presentation.mentoring.dto.MentoringResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentoringService {
    private final MentoringRepository mentoringRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

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
                mentee);

        mentee.addMentoring(mentoring);
        mentor.addMentoring(mentoring);

        return MentoringResponse.from(mentoringRepository.save(mentoring));
    }

    /**
     * 멘토링 신청 일정 변경
     */
    @Transactional
    public void changeMentoringDate(EditMentoringTimeRequest request) {
        Long mentoringId = request.mentoringId();
        Mentoring mentoring = mentoringRepository.findById(mentoringId)
                .orElseThrow(() -> new CustomRuntimeException(MentoringErrorCode.MENTORING_NOT_FOUND));
        mentoring.changeApplyTimes(request.applyTimes());
    }
}
