package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.vo.Days;
import es.princip.ringus.domain.mentor.vo.Timezone;

import java.time.LocalTime;
import java.util.Set;

public record TimezoneRequest (
        Set<Days> days,
        LocalTime startTime,
        LocalTime endTime
) {
    public Timezone toEntity() {
        return new Timezone(days, startTime, endTime);
    }
}