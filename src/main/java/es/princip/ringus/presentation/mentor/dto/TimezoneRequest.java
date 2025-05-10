package es.princip.ringus.presentation.mentor.dto;

import es.princip.ringus.domain.mentor.vo.Timezone;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalTime;
import java.util.List;

public record TimezoneRequest (
        @UniqueElements List<String> days,
        LocalTime startTime,
        LocalTime endTime
) {
    public Timezone toEntity() {
        return new Timezone(days, startTime, endTime);
    }
}