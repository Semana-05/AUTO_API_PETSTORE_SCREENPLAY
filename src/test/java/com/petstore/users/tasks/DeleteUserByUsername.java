package com.petstore.users.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteUserByUsername implements Task {

    private final String username;

    public DeleteUserByUsername(String username) {
        this.username = username;
    }

    public static DeleteUserByUsername called(String username) {
        return Tasks.instrumented(DeleteUserByUsername.class, username);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/user/{username}").with(request -> request.pathParam("username", username))
        );
    }
}
