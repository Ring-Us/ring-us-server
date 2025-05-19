-- mentor 테이블 컬럼명 변경 및 컬럼 추가
ALTER TABLE mentor
    CHANGE COLUMN file_name portfolio_file_name VARCHAR(255) NOT NULL,
    CHANGE COLUMN file_path portfolio_file_path VARCHAR(255) NOT NULL,
    ADD COLUMN profile_image_file_name VARCHAR(255),
    ADD COLUMN profile_image_file_path VARCHAR(255),
    ADD COLUMN portfolio_file_size INT,
    ADD COLUMN profile_image_file_size INT;

-- mentee 테이블 컬럼명 변경 및 컬럼 추가
ALTER TABLE mentee
    CHANGE COLUMN file_name profile_image_file_name VARCHAR(255) NOT NULL,
    CHANGE COLUMN file_path profile_image_file_path VARCHAR(255) NOT NULL,
    ADD COLUMN profile_image_file_size INT;