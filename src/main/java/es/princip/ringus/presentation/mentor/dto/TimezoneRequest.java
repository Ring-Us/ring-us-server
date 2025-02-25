package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.vo.Timezone;

import java.time.LocalTime;

public record TimezoneRequest (
        LocalTime startTime,
        LocalTime endTime
) {
    public Timezone toEntity() {
        return new Timezone(startTime, endTime);
    }
}