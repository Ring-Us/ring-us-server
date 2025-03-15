CREATE TABLE mentoring (

    mentoring_id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    mentoring_status ENUM ('WAITING', 'ACCEPTED', 'REJECTED') NOT NULL,
    mentoring_topic ENUM ('STUDY', 'INDUSTRY_TRENDS', 'INTERVIEW', 'JOB_PREP') NOT NULL,

    apply_times VARCHAR(500) NOT NULL CHECK (JSON_VALID(apply_times)),
    mentoring_message VARCHAR(500) NOT NULL,

    mentee_id BIGINT NOT NULL,
    mentor_id BIGINT NOT NULL,

    PRIMARY KEY (mentoring_id),

    CONSTRAINT fk_mentoring_mentee FOREIGN KEY (mentee_id) REFERENCES mentee (mentee_id) ON DELETE CASCADE,
    CONSTRAINT fk_mentoring_mentor FOREIGN KEY (mentor_id) REFERENCES mentor (mentor_id) ON DELETE CASCADE
);