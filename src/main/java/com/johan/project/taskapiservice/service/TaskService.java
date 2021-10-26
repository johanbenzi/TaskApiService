package com.johan.project.taskapiservice.service;

import com.johan.project.taskapiservice.exception.InvalidTaskException;
import com.johan.project.taskapiservice.exception.InvalidUserException;
import com.johan.project.taskapiservice.model.request.TaskRequest;
import com.johan.project.taskapiservice.repository.TasksRepository;
import com.johan.project.taskapiservice.repository.UsersRepository;
import com.johan.project.taskapiservice.repository.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UsersRepository usersRepository;

    private final TasksRepository tasksRepository;

    public Long createTaskForUser(final Long userId, final TaskRequest taskRequest) {

        final var user = usersRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidUserException("User doesn't exist"));

        return tasksRepository.save(TaskEntity
                .builder()
                .user(user)
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .build()).getId();
    }

    public void deleteTaskForUser(final Long userId, final Long taskId) {

        final var task = tasksRepository
                .findByIdAndUserId(userId, taskId)
                .orElseThrow(() -> new InvalidTaskException("Task doesn't exist"));

        tasksRepository.delete(task);

    }
}
