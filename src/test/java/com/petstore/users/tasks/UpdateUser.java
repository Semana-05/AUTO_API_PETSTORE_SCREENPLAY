package com.petstore.users.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.Map;

public class UpdateUser implements Task {

    private final String username;
    private final Map<String, Object> userPayload;

    public UpdateUser(String username, Map<String, Object> userPayload) {
        this.username = username;
        this.userPayload = userPayload;
    }

    public static UpdateUser called(String username, Map<String, Object> userPayload) {
        return Tasks.instrumented(UpdateUser.class, username, userPayload);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/user/{username}").with(request -> request
                        .pathParam("username", username)
                        .contentType("application/json")
                        .body(userPayload)
                )
        );
    }
}
