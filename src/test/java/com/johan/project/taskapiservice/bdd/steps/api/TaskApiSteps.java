package com.johan.project.taskapiservice.bdd.steps.api;


import io.cucumber.java.After;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class TaskApiSteps {
    @Steps
    private CommonAPISteps commonAPISteps;

    @After
    public void afterScenario() {
        commonAPISteps.afterScenario();
    }

    @Step("Save username in serenity session")
    public void saveUserName(final String userName) {
        commonAPISteps.saveUserName(userName);
    }

    @Step("Create a user through API")
    public void createUser() {
        commonAPISteps.createUser();
    }

    @Step("Assert response code")
    public void assertResponseCode(final int responseCode) {
        commonAPISteps.assertResponseCode(responseCode);
    }

    @Step("Assert the response is non null")
    public void assertNonNullResponse() {
        commonAPISteps.assertNonNullResponse();
    }
}
