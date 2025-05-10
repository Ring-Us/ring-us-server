package es.princip.ringus.presentation.common.dto;

import es.princip.ringus.domain.mentor.vo.Timezone;

import java.time.LocalTime;
import java.util.List;

public record TimezoneResponse(
        List<String> days,
        LocalTime startTime,
        LocalTime endTime
) {
    public static TimezoneResponse from(final Timezone timezone) {
        return new TimezoneResponse(
                timezone.getDaysKor(),
                timezone.getStartTime(),
                timezone.getEndTime()
        );
    }
}
