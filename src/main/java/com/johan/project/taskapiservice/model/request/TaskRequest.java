package com.johan.project.taskapiservice.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TaskRequest {

    @Schema(description = "Task Title", example = "Task A")
    String title;

    @Schema(description = "Task Description", example = "Lorem ipsum")
    String description;
}
