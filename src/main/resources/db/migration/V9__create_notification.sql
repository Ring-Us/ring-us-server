CREATE TABLE notification (
    notification_id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(500) NOT NULL,
    type ENUM (
        'MENTORING_REQUEST',
        'MENTORING_APPROVED',
        'MENTORING_REJECTED'
        ) NOT NULL,
    is_read TINYINT(1) NOT NULL DEFAULT 0,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),

    PRIMARY KEY (notification_id),
    CONSTRAINT fk_notification_sender
        FOREIGN KEY (sender_id)   REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_notification_receiver
        FOREIGN KEY (receiver_id) REFERENCES member(member_id) ON DELETE CASCADE
);