package com.wompi.api.task;


import net.serenitybdd.annotations.Shared;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerTokensLegales  implements Task {


    private static final String TOKEN_KEY = "ACCEPTANCE_TOKEN";


    public static ObtenerTokensLegales delMerchant() {
        return instrumented(ObtenerTokensLegales.class);
    }

    @Override

    public <T extends Actor> void performAs(T actor) {

        EnvironmentVariables environmentVariables =
                net.serenitybdd.core.Serenity.environmentVariables();

        String publicKey =
                environmentVariables.getProperty("wompi.publicKey");

        actor.attemptsTo(
                Get.resource("/v1/merchants/" + publicKey)
        );

        String acceptanceToken  =
                SerenityRest.lastResponse()
                        .jsonPath()
                        .getString("data.presigned_acceptance.acceptance_token");

        actor.remember(TOKEN_KEY, acceptanceToken);
        SerenityRest.lastResponse().prettyPrint();

    }


}