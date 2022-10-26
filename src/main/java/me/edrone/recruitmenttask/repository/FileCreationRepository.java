package me.edrone.recruitmenttask.repository;

import me.edrone.recruitmenttask.entity.FileCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCreationRepository extends JpaRepository <FileCreation,Long>{
}
