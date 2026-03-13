package com.petstore.users.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LastResponseStatusCode implements Question<Integer> {

    public static LastResponseStatusCode value() {
        return new LastResponseStatusCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }
}
