package com.wompi.api.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TransaccionExiste implements Question<Boolean> {

    public static TransaccionExiste esValida() {
        return new TransaccionExiste();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return SerenityRest.lastResponse()
                .jsonPath()
                .getString("data.id") != null;
    }
}