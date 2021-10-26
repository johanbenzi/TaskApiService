package com.johan.project.taskapiservice.bdd.steps.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johan.project.taskapiservice.TaskApiServiceApplication;
import com.johan.project.taskapiservice.repository.TasksRepository;
import com.johan.project.taskapiservice.repository.UsersRepository;
import io.cucumber.java.After;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = TaskApiServiceApplication.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles(value = "local")
@ContextConfiguration
@Log4j2
public class CommonAPISteps {

    @LocalServerPort
    private int port;

    private String taskApiServiceUrl;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @After
    public void afterScenario() {
        tasksRepository.deleteAll();
        usersRepository.deleteAll();
    }

    public void saveUserName(final String userName) {
        Serenity.getCurrentSession().put("UserName", userName);
    }

    public void createUser() {
        setTaskApiServiceUrl();
        SerenityRest.given()
                .when()
                .body(Serenity.getCurrentSession().get("UserName"))
                .put(taskApiServiceUrl + "/user");
    }

    public void assertResponseCode(final int responseCode) {
        assertThat(SerenityRest.then().extract().statusCode(), is(responseCode));
    }

    public void assertNonNullResponse() {
        Serenity.getCurrentSession().put("ResponseID", SerenityRest.then().extract().response().body().as(Long.class));
        assertThat(SerenityRest.then().extract().response().body().as(Long.class), notNullValue());
    }

    private void setTaskApiServiceUrl() {
        SerenityRest.clear();
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        try {
            taskApiServiceUrl = format("http://%s:%s", InetAddress.getLocalHost().getHostAddress(), port);
        } catch (final UnknownHostException e) {
            log.error(e, e);
        }
    }

}
