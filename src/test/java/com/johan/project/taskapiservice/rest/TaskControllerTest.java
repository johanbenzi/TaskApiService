package com.johan.project.taskapiservice.rest;

import com.johan.project.taskapiservice.model.request.TaskRequest;
import com.johan.project.taskapiservice.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskController Unit Tests")
class TaskControllerTest {

    @InjectMocks
    private TaskController cut;

    @Mock
    private TaskService taskService;

    private TaskRequest taskRequest;

    private static final String TITLE = "Task A";

    private static final Long USER_ID = 123L;

    private static final Long TASK_ID = 1L;

    private static final String DESCRIPTION = "Task description";

    @Test
    void createTaskForUser() {
        taskRequest = TaskRequest.builder().title(TITLE).description(DESCRIPTION).build();
        when(taskService.createTaskForUser(USER_ID, taskRequest)).thenReturn(TASK_ID);

        final var taskId = cut.createTaskForUser(USER_ID, taskRequest);

        Assertions.assertEquals(TASK_ID, taskId);
        verify(taskService, times(1)).createTaskForUser(USER_ID, taskRequest);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void createTaskForUser_nullUserId() {
        taskRequest = TaskRequest.builder().title(TITLE).description(DESCRIPTION).build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> cut.createTaskForUser(null, taskRequest));

        verifyNoInteractions(taskService);
    }

    @Test
    void createTaskForUser_nullTaskRequest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cut.createTaskForUser(USER_ID, null));

        verifyNoInteractions(taskService);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void createTaskForUser_blankTitle(final String title) {
        taskRequest = TaskRequest.builder().title(title).description(DESCRIPTION).build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> cut.createTaskForUser(USER_ID, taskRequest));

        verifyNoInteractions(taskService);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void createTaskForUser_blankDescription(final String description) {
        taskRequest = TaskRequest.builder().title(TITLE).description(description).build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> cut.createTaskForUser(USER_ID, taskRequest));

        verifyNoInteractions(taskService);
    }

}