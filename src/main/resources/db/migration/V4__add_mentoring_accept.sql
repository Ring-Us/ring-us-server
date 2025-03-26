CREATE TABLE mentoring_accept (
    mentoring_accept_id BIGINT NOT NULL AUTO_INCREMENT,
    mentoring_time VARCHAR(200) NOT NULL,
    mentor_id BIGINT NOT NULL,
    mentee_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (mentoring_accept_id),

    CONSTRAINT fk_mentoring_accept_mentor
      FOREIGN KEY (mentor_id) REFERENCES mentor(mentor_id) ON DELETE CASCADE,

    CONSTRAINT fk_mentoring_accept_mentee
      FOREIGN KEY (mentee_id) REFERENCES mentee(mentee_id) ON DELETE CASCADE
);