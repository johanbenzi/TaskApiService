package com.johan.project.taskapiservice.model.response;

import com.johan.project.taskapiservice.constants.AppConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TaskResponse {

    @Schema(description = "Task Title", example = "Task A")
    String title;

    @Schema(description = "Task Description", example = "Lorem ipsum")
    String description;

    @Schema(description = "Task created time", example = "2021-10-26 10:00:00", pattern = AppConstants.DATE_FORMAT_STRING)
    String createdTime;

    @Schema(description = "Task last modified time", example = "2021-10-26 10:20:00", pattern = AppConstants.DATE_FORMAT_STRING)
    String lastModifiedTime;
}
