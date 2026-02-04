package com.wompi.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class DatosDeLaTransaccion implements Question<Boolean> {

    public static DatosDeLaTransaccion sonCorrectos() {
        return new DatosDeLaTransaccion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        String transactionId =
                actor.recall("TRANSACTION_ID");

        String responseId =
                SerenityRest.lastResponse()
                        .jsonPath()
                        .getString("data.id");

        Integer amount =
                SerenityRest.lastResponse()
                        .jsonPath()
                        .getInt("data.amount_in_cents");

        String currency =
                SerenityRest.lastResponse()
                        .jsonPath()
                        .getString("data.currency");

        return transactionId.equals(responseId)
                && amount != null && amount > 0
                && currency != null && currency.equals("COP");
    }
}