package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringStatus;
import es.princip.ringus.domain.mentoring.MentoringTime;

import java.util.List;

public record MentoringResponse(
        Long mentoringId,
        MentoringStatus status,
        String topic,
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
                mentoring.getMentoringTopic().getKor(),
                mentoring.getApplyTimes(),
                mentoring.getMentoringMessage(),
                mentoring.getMentor().getNickname(),
                mentoring.getMentee().getNickname()
        );
    }
}
