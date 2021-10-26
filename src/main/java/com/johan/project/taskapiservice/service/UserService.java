package com.johan.project.taskapiservice.service;

import com.johan.project.taskapiservice.exception.InvalidUserException;
import com.johan.project.taskapiservice.model.response.TaskResponse;
import com.johan.project.taskapiservice.repository.UsersRepository;
import com.johan.project.taskapiservice.repository.entity.TaskEntity;
import com.johan.project.taskapiservice.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    public Long createUser(final String userName) {
        return usersRepository.save(UserEntity.builder().userName(userName).build()).getId();
    }

    public List<TaskResponse> getTasksForUser(final Long userId) {
        return usersRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidUserException("User doesn't exist"))
                .getTasks()
                .stream()
                .map(TaskEntity::toResponse)
                .collect(Collectors.toList());
    }
}
