package com.johan.project.taskapiservice.repository;

import com.johan.project.taskapiservice.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findById(Long id);

    Optional<TaskEntity> findByIdAndUserId(Long id, Long userId);
}
