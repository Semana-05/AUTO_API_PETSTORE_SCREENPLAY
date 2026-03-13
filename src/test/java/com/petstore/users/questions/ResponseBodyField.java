package com.petstore.users.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseBodyField implements Question<String> {

    private final String fieldName;

    public ResponseBodyField(String fieldName) {
        this.fieldName = fieldName;
    }

    public static ResponseBodyField called(String fieldName) {
        return new ResponseBodyField(fieldName);
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString(fieldName);
    }
}
