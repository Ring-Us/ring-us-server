package es.princip.ringus.domain.mentoring.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.princip.ringus.domain.exception.MentoringErrorCode;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class MentoringTimeConverter implements AttributeConverter<MentoringTime, String> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public String convertToDatabaseColumn(MentoringTime attribute) {
        try {
            if (attribute == null) {
                return "[]";
            }
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            log.error("Failed to convert MentoringTime to JSON {}", e.getMessage());
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }

    @Override
    public MentoringTime convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, MentoringTime.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert JSON to MentoringTime: {}", e.getMessage());
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }
}
