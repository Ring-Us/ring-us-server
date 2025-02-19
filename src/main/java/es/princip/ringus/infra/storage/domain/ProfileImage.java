package es.princip.ringus.infra.storage.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage extends File {

    @Builder
    public ProfileImage(String fileName, String filePath) {
        super(fileName, filePath);
    }

}
