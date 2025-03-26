package es.princip.ringus.domain.mentoring;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "멘토링 상태")
public enum MentoringStatus {
    @Schema(description = "대기중 (초기 미확정 상태)")
    WAITING,
    @Schema(description = "수락됨 (멘토가 요청을 수락한 상태)")
    ACCEPTED,
    @Schema(description = "거절됨 (멘토가 요청을 거절한 상태)")
    REJECTED,
    @Schema(description = "결제 전 취소 (멘티가 결제 전에 신청을 취소한 상태)")
    CANCELLED_BEFORE_PAYMENT,
    @Schema(description = "결제 후 취소 (멘티가 결제 후 환불 요청한 상태)")
    CANCELLED_AFTER_PAYMENT,
    @Schema(description = "멘토링 완료 (멘토링이 정상적으로 진행된 후 완료된 상태)")
    COMPLETED
}
