package me.edrone.recruitmenttask.repository;

import me.edrone.recruitmenttask.entity.StringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringRepository extends JpaRepository<StringEntity, Long> {
}
