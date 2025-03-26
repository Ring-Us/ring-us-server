package es.princip.ringus.infra.storage.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMemberRepository extends JpaRepository<FileMember, Long> {
}