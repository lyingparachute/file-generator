package me.edrone.recruitmenttask.repository;

import me.edrone.recruitmenttask.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}