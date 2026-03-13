package com.petstore.users.stepdefinitions.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hook {

    @Before
    public void initScenario() {
        OnStage.setTheStage(new OnlineCast());
    }
}
