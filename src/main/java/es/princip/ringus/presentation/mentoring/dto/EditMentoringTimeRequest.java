package es.princip.ringus.presentation.mentoring.dto;

import es.princip.ringus.domain.mentoring.MentoringTime;
import java.util.List;

public record EditMentoringTimeRequest(
    Long mentoringId,
    List<MentoringTime> applyTimes
) {
}