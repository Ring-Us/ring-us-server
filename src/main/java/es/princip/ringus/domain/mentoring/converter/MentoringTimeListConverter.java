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
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Converter
public class MentoringTimeListConverter implements AttributeConverter<List<MentoringTime>,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public String convertToDatabaseColumn(List<MentoringTime> mentoringTimes) {
        try {
            if (mentoringTimes == null || mentoringTimes.isEmpty()) {
                return "[]"; // 빈 리스트일 경우 빈 JSON 배열 반환
            }
            String json = objectMapper.writeValueAsString(mentoringTimes);
            log.info("Success to convert MentoringTime to JSON: {}", json);
            return json;
        } catch (Exception e) {
            log.error("Failed to convert MentoringTime to JSON {}", e.getMessage());
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
            log.info("Success to convert JSON to MentoringTime: {}", times);
            return times;
        } catch (JsonProcessingException e) {
            log.error("Failed to convert JSON to MentoringTime: {}", e.getMessage());
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }
}
