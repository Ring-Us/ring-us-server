package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Timezone;

import java.time.LocalTime;
import java.util.Set;

public record TimezoneResponse(
        Set<String> days,
        LocalTime startTime,
        LocalTime endTime
) {
    public static TimezoneResponse of(final Timezone timezone) {
        return new TimezoneResponse(
                timezone.getDays(),
                timezone.getStartTime(),
                timezone.getEndTime()
        );
    }
}
