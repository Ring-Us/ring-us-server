package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringStatus;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.mentoring.MentoringTopic;

import java.util.List;

public record MentoringResponse(
        Long mentoringId,
        MentoringStatus status,
        MentoringTopic topic,
        List<MentoringTime> applyTimes,
        String mentoringMessage,
        String mentorName,
        String menteeName
) {
    public static MentoringResponse from(
            Mentoring mentoring
    ) {
        return new MentoringResponse(
                mentoring.getId(),
                mentoring.getMentoringStatus(),
                mentoring.getMentoringTopic(),
                mentoring.getApplyTimes(),
                mentoring.getMentoringMessage(),
                mentoring.getMentor().getNickname(),
                mentoring.getMentee().getNickname()
        );
    }
}
