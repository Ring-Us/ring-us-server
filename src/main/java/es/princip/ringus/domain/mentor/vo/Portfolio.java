package es.princip.ringus.domain.mentor.vo;

import es.princip.ringus.infra.storage.domain.File;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio extends File {

    @Builder
    public Portfolio(String fileName, String filePath, Integer fileSize) {
        super(fileName, filePath, fileSize);
    }
}
