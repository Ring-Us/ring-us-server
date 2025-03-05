package es.princip.ringus.domain.mentoring;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentoringTime {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public MentoringTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("시작 시간과 종료 시간은 필수입니다.");
        }
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("종료 시간은 시작 시간보다 뒤에 있어야 합니다.");
        }
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("시작 시간이 현재 시간보다 빠를 수 없습니다.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
