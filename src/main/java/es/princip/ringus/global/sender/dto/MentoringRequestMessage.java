package es.princip.ringus.global.sender.dto;

import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentoring.Mentoring;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.mentoring.MentoringTopic;

import java.util.List;

public record MentoringRequestMessage(
        Long receiverId,
        Long senderId,
        String menteeName,
        String mentorName,
        String mentoringMessage,
        MentoringTopic mentoringTopic,
        List<MentoringTime> applyTimes
) {
    public static MentoringRequestMessage from(
        final Mentee mentee,
        final Mentor mentor,
        final Mentoring mentoring
    ) {
        return new MentoringRequestMessage(
            mentor.getMemberId(),
            mentee.getMemberId(),
            mentee.getNickname(),
            mentor.getNickname(),
            mentoring.getMentoringMessage(),
            mentoring.getMentoringTopic(),
            mentoring.getApplyTimes()
        );
    }
}
