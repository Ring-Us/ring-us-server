package es.princip.ringus.domain.mentoring.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.exception.MentoringErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = false)
public class MentoringTimeConverter implements AttributeConverter<List<MentoringTime>,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public String convertToDatabaseColumn(List<MentoringTime> mentoringTimes) {
        try {
            if (mentoringTimes == null || mentoringTimes.isEmpty()) {
                return "[]"; // 빈 리스트일 경우 빈 JSON 배열 반환
            }
            String json = objectMapper.writeValueAsString(mentoringTimes);
            System.out.println("✅ MentoringTime JSON 변환 (DB 저장): " + json);
            return json;
        } catch (Exception e) {
            System.err.println("❌ MentoringTime 변환 오류 (DB 저장): " + mentoringTimes);
            e.printStackTrace();
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }

    @Override
    public List<MentoringTime> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty()) {
                return new ArrayList<>(); // null 또는 빈 값이면 빈 리스트 반환
            }
            List<MentoringTime> times = objectMapper.readValue(dbData, new TypeReference<List<MentoringTime>>() {});
            System.out.println("✅ MentoringTime 객체 변환 (조회 시): " + times);
            return times;
        } catch (JsonProcessingException e) {
            System.err.println("❌ MentoringTime 변환 오류 (조회 시): " + dbData);
            e.printStackTrace();
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }
}
