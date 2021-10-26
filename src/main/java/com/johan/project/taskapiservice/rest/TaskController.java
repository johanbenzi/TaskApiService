package com.johan.project.taskapiservice.rest;

import com.johan.project.taskapiservice.model.request.TaskRequest;
import com.johan.project.taskapiservice.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Handles requests to do CRUD operations on categories")
public class TaskController {

    private final TaskService taskService;

    @PutMapping(path = "/user/{user-id}/task")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a task for a user")
    @ApiResponse(responseCode = "200", description = "Create ", content = {
            @Content(examples = {@ExampleObject(value = "123")})})
    @ApiResponse(responseCode = "400", description = "Error in creating user, bad data found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    public Long createTaskForUser(@Parameter(name = "user-id", required = true, example = "1")
                                  @PathVariable(value = "user-id") final Long userId,
                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task Request")
                                  @RequestBody final TaskRequest taskRequest) {
        Preconditions.checkArgument(Objects.nonNull(userId), "UserId cannot be null");
        Preconditions.checkArgument(Objects.nonNull(taskRequest), "TaskRequest cannot be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(taskRequest.getTitle()), "Title cannot be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(taskRequest.getDescription()), "Description cannot be null");

        return taskService.createTaskForUser(userId, taskRequest);
    }

    @DeleteMapping(path = "/user/{user-id}/task/{task-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a task for a user")
    @ApiResponse(responseCode = "204", description = "Success. Task deleted")
    @ApiResponse(responseCode = "400", description = "Error in deleting task, bad data found", content = @Content)
    @ApiResponse(responseCode = "404", description = "Error in deleting task, task not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    public void deleteTaskForUser(@Parameter(name = "user-id", required = true, example = "1")
                                  @PathVariable(value = "user-id") final Long userId,
                                  @Parameter(name = "task-id", required = true, example = "1")
                                  @PathVariable(value = "task-id") final Long taskId) {
        Preconditions.checkArgument(Objects.nonNull(userId), "UserId cannot be null");
        Preconditions.checkArgument(Objects.nonNull(taskId), "TaskId cannot be null");

        taskService.deleteTaskForUser(userId, taskId);
    }
}