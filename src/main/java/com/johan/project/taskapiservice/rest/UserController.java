package com.johan.project.taskapiservice.rest;

import com.johan.project.taskapiservice.model.response.TaskResponse;
import com.johan.project.taskapiservice.service.UserService;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Handles requests to do CRUD operations on categories")
public class UserController {

    private final UserService userService;

    @PutMapping(path = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a user")
    @ApiResponse(responseCode = "201", description = "Success. User created", content = {
            @Content(examples = {@ExampleObject(value = "123")})})
    @ApiResponse(responseCode = "400", description = "Error in creating user, bad data found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    public Long createUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User name", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {@ExampleObject(value = "John Doe")})})
                           @RequestBody final String userName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(userName), "Username cannot be blank");
        return userService.createUser(userName);
    }

    @GetMapping(path = "/user/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all tasks for a user")
    @ApiResponse(responseCode = "200", description = "Success. User created", content = {
            @Content(examples = {@ExampleObject(value = "123")})})
    @ApiResponse(responseCode = "400", description = "Error in creating user, bad data found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    public List<TaskResponse> getTasksForUser(@Parameter(name = "user-id", required = true, example = "1")
                                              @PathVariable(value = "user-id") final Long userId) {
        Preconditions.checkArgument(Objects.nonNull(userId), "UserId cannot be null");
        return userService.getTasksForUser(userId);
    }
}
