package es.princip.ringus.domain.apply.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.princip.ringus.domain.apply.ApplyTime;
import es.princip.ringus.domain.exception.ApplyErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = false)
public class ApplyTimeConverter implements AttributeConverter<List<ApplyTime>,String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<ApplyTime> applyTimes) {
        try {
            return objectMapper.writeValueAsString(applyTimes);
        } catch (Exception e) {
            throw new RuntimeException(ApplyErrorCode.APPLY_TIME_CONVERT_ERROR.message());
        }
    }

    @Override
    public List<ApplyTime> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<ApplyTime>>() {});
        } catch (JsonProcessingException e) {
            throw new CustomRuntimeException(ApplyErrorCode.APPLY_TIME_CONVERT_ERROR);
        }
    }
}
