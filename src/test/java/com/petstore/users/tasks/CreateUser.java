package com.petstore.users.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class CreateUser implements Task {

    private final Map<String, Object> userPayload;

    public CreateUser(Map<String, Object> userPayload) {
        this.userPayload = userPayload;
    }

    public static CreateUser withData(Map<String, Object> userPayload) {
        return Tasks.instrumented(CreateUser.class, userPayload);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/user").with(request -> request
                        .contentType("application/json")
                        .body(userPayload)
                )
        );
    }
}
