package es.princip.ringus.domain.mentoring.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.princip.ringus.domain.mentoring.MentoringTime;
import es.princip.ringus.domain.exception.MentoringErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = false)
public class MentoringTimeConverter implements AttributeConverter<List<MentoringTime>,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<MentoringTime> applyTimes) {
        try {
            return objectMapper.writeValueAsString(applyTimes);
        } catch (Exception e) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }

    @Override
    public List<MentoringTime> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<MentoringTime>>() {});
        } catch (JsonProcessingException e) {
            throw new CustomRuntimeException(MentoringErrorCode.MENTORING_TIME_CONVERT_ERROR);
        }
    }
}
