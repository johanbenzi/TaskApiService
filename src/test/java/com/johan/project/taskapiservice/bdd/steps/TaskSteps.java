package com.johan.project.taskapiservice.bdd.steps;

import com.johan.project.taskapiservice.bdd.steps.api.TaskApiSteps;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class TaskSteps {

    @After
    public void afterScenario() {
        steps.afterScenario();
    }

    @Steps
    private TaskApiSteps steps;

    @Given("A username {word}")
    public void saveUserName(final String userName) {
        steps.saveUserName(userName);
    }

    @When("The user is attempted to be created")
    public void createUser() {
        steps.createUser();
    }

    @Then("A response code of {int} is obtained")
    public void assertResponseCode(final int responseCode) {
        steps.assertResponseCode(responseCode);
    }

    @And("An ID is returned")
    public void assertNonNullResponse() {
        steps.assertNonNullResponse();
    }

}
