package es.princip.ringus.domain.mentor.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timezone {
    private LocalTime startTime;
    private LocalTime endTime;

    private void validateTime(LocalTime time) {
        LocalTime earliest = LocalTime.of(8, 0);
        LocalTime latest = LocalTime.of(23, 30);
        if (time.isBefore(earliest) || time.isAfter(latest) || time.getMinute() % 30 != 0) {
            throw new IllegalArgumentException("Time must be between 8:00 AM and 11:30 PM in 30-minute intervals");
        }
    }
}