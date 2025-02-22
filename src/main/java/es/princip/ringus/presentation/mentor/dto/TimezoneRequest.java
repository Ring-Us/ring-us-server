package es.princip.ringus.presentation.mentor.dto;

import java.time.LocalTime;

public record TimezoneRequest (
        LocalTime startTime,
        LocalTime endTime
) {
}