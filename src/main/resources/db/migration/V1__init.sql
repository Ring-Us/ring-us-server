
CREATE TABLE `file_member` (
                               `is_verified` bit(1) DEFAULT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `file_member_id` bigint NOT NULL AUTO_INCREMENT,
                               `member_id` bigint DEFAULT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `file_path` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`file_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `member` (
                          `is_file_verified` bit(1) NOT NULL,
                          `is_profile_registered` bit(1) NOT NULL,
                          `is_university_verified` bit(1) NOT NULL,
                          `created_at` datetime(6) DEFAULT NULL,
                          `member_id` bigint NOT NULL AUTO_INCREMENT,
                          `updated_at` datetime(6) DEFAULT NULL,
                          `email` varchar(255) NOT NULL,
                          `password` varchar(255) NOT NULL,
                          `member_type` enum('ADMIN','MENTEE','MENTOR') DEFAULT NULL,
                          PRIMARY KEY (`member_id`),
                          UNIQUE KEY `UKmbmcqelty0fbrvxp1q58dn57t` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mentee` (
                          `member_id` bigint DEFAULT NULL,
                          `mentee_id` bigint NOT NULL AUTO_INCREMENT,
                          `introduction` varchar(500) DEFAULT NULL,
                          `file_name` varchar(255) NOT NULL,
                          `file_path` varchar(255) NOT NULL,
                          `major` varchar(255) DEFAULT NULL,
                          `nickname` varchar(255) DEFAULT NULL,
                          `school_name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`mentee_id`),
                          UNIQUE KEY `UK1esk9x4yr6uocrt4obtej4wie` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mentor` (
                          `end_time` time(6) DEFAULT NULL,
                          `experience` int DEFAULT NULL,
                          `start_time` time(6) DEFAULT NULL,
                          `member_id` bigint DEFAULT NULL,
                          `mentor_id` bigint NOT NULL AUTO_INCREMENT,
                          `message` varchar(50) DEFAULT NULL,
                          `introduction` varchar(500) DEFAULT NULL,
                          `description` varchar(255) DEFAULT NULL,
                          `file_name` varchar(255) NOT NULL,
                          `file_path` varchar(255) NOT NULL,
                          `major` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `nickname` varchar(255) DEFAULT NULL,
                          `role` varchar(255) DEFAULT NULL,
                          `school_name` varchar(255) DEFAULT NULL,
                          `url` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`mentor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mentor_hashtags` (
                                   `mentor_id` bigint NOT NULL,
                                   `value` varchar(255) DEFAULT NULL,
                                   KEY `FKt3y0r48c7ok5lur8k568yt8kx` (`mentor_id`),
                                   CONSTRAINT `FKt3y0r48c7ok5lur8k568yt8kx` FOREIGN KEY (`mentor_id`) REFERENCES `mentor` (`mentor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mentor_mentoring_fields` (
                                           `mentor_id` bigint NOT NULL,
                                           `mentoring_field` enum('INTERVIEW_PREPARATION','JOB_PREPARATION','PORTFOLIO','PRACTICAL_SKILLS') DEFAULT NULL,
                                           KEY `FK97ao36m77up6wdjhov0y93pc2` (`mentor_id`),
                                           CONSTRAINT `FK97ao36m77up6wdjhov0y93pc2` FOREIGN KEY (`mentor_id`) REFERENCES `mentor` (`mentor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `service_term` (
                                `required` bit(1) DEFAULT NULL,
                                `service_term_tag` varchar(255) NOT NULL,
                                PRIMARY KEY (`service_term_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- `service_term` 테이블에 3개 데이터 삽입
INSERT INTO `service_term` (`service_term_tag`, `required`) VALUES
                                                                ('TERMS_OF_SERVICE', 1),
                                                                ('PRIVACY_POLICY', 1),
                                                                ('MARKETING_CONSENT', 0);
CREATE TABLE `service_term_agreement` (
                                          `agreed` bit(1) DEFAULT NULL,
                                          `agreed_at` datetime(6) DEFAULT NULL,
                                          `created_at` datetime(6) DEFAULT NULL,
                                          `member_id` bigint NOT NULL,
                                          `updated_at` datetime(6) DEFAULT NULL,
                                          `tag` varchar(255) DEFAULT NULL,
                                          KEY `FKl7bp8w5ehipkfnre066psfv7x` (`member_id`),
                                          CONSTRAINT `FKl7bp8w5ehipkfnre066psfv7x` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
