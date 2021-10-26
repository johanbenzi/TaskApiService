package com.johan.project.taskapiservice.bdd;

import com.johan.project.taskapiservice.bdd.classrule.PostgresContainerClassRule;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"com.johan.project.taskapiservice.bdd.steps"})
public class CucumberAT {

    @ClassRule
    public static final PostgresContainerClassRule postgresContainerClassRule = new PostgresContainerClassRule();
}
