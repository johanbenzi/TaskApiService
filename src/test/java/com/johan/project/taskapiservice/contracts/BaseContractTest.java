package com.johan.project.taskapiservice.contracts;

import com.johan.project.taskapiservice.exception.InvalidTaskException;
import com.johan.project.taskapiservice.exception.InvalidUserException;
import com.johan.project.taskapiservice.model.request.TaskRequest;
import com.johan.project.taskapiservice.service.TaskService;
import com.johan.project.taskapiservice.service.UserService;
import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
@ActiveProfiles(value = "local")
@DirtiesContext
@Testcontainers
@Disabled
public class BaseContractTest {

    @Container
    private static final PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("tasks")
            .withUsername("sqladmin")
            .withPassword("secret");

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserService userService;

    @BeforeAll
    static void init() {
        System.setProperty("DB_HOST", postgresqlContainer.getContainerIpAddress());
        System.setProperty("DB_PORT", postgresqlContainer.getMappedPort(5432).toString());
    }

    @BeforeEach
    void setup() {
        final EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.webAppContextSetup(this.context);

        final var taskRequest = TaskRequest.builder().title("Some task").description("Do this well").build();

        when(userService.createUser(anyString())).thenReturn(123L);

        when(taskService.createTaskForUser(eq(1L), any(TaskRequest.class))).thenReturn(1L);

        when(taskService.createTaskForUser(eq(2L), any(TaskRequest.class))).thenThrow(new InvalidUserException("Invalid user"));

        doThrow(new InvalidTaskException("Task doesn't exist"))
                .when(taskService).deleteTaskForUser(1L, 2L);
    }
}
