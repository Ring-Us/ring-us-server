package es.princip.ringus.domain.mentor.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Days {
    MON("MON", "월"),
    TUE("TUE", "화"),
    WED("WED", "수"),
    THU("THU", "목"),
    FRI("FRI", "금"),
    SAT("SAT", "토"),
    SUN("SUN", "일");

    private final String code;
    private final String kor;

    Days(String code, String kor) {
        this.code = code;
        this.kor = kor;
    }

    public static String toKor(String code) {
        return Arrays.stream(values())
            .filter(e -> e.code.equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() ->
                    new IllegalArgumentException("매핑되는 Days가 없습니다: " + code)
            ).kor;
    }

    public static String fromCode(String kor) {
        return Arrays.stream(values())
            .filter(e -> e.kor.equals(kor))
            .findFirst()
            .orElseThrow(
                    () -> new IllegalArgumentException("매핑되는 Days가 없습니다: " + kor)
            ).code;
    }
}