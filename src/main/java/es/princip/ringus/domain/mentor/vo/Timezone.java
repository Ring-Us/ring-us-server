package es.princip.ringus.domain.mentor.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timezone {

    private String days;
    private LocalTime startTime;
    private LocalTime endTime;

    private void validateTime(LocalTime time) {
        LocalTime earliest = LocalTime.of(8, 0);
        LocalTime latest = LocalTime.of(23, 30);
        if (time.isBefore(earliest) || time.isAfter(latest) || time.getMinute() % 30 != 0) {
            throw new IllegalArgumentException("Time must be between 8:00 AM and 11:30 PM in 30-minute intervals");
        }
    }

    public Timezone(
        final Set<Days> days,
        final LocalTime startTime,
        final LocalTime endTime
    ) {
        this.days = days.stream()
            .map(Days::name)
            .collect(Collectors.joining(", "));
        validateTime(startTime);
        this.startTime = startTime;
        validateTime(endTime);
        this.endTime = endTime;
    }

    public Set<Days> getDays() {
        return Set.of(days.split(", ")).stream()
            .map(Days::valueOf)
            .collect(Collectors.toSet());
    }
}