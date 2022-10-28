package me.edrone.recruitmenttask.repository;

import me.edrone.recruitmenttask.entity.FileEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    @EntityGraph(attributePaths = "setOfStrings")
    List<FileEntity> findAll();
}