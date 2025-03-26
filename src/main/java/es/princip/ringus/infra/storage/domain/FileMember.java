package es.princip.ringus.infra.storage.domain;

import es.princip.ringus.domain.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class FileMember extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_member_id")
    private Long id;

    private Long memberId;

    private String filePath;

    private Boolean isVerified;

    @Builder
    public FileMember(
            final Long memberId,
            final String filePath
    ) {
       this.memberId = memberId;
       this.filePath = filePath;
       this.isVerified = false;
    }
}
