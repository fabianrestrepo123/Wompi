package com.wompi.api.task;


import com.wompi.api.utils.WompiConfig;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class ConsultarTransaccionBancaria implements Task {


    public static ConsultarTransaccionBancaria porId() {
        return Tasks.instrumented(ConsultarTransaccionBancaria.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EnvironmentVariables environmentVariables =
                net.serenitybdd.core.Serenity.environmentVariables();

        WompiConfig config = new WompiConfig(environmentVariables);

        String transactionId = actor.recall("TRANSACTION_ID");

        actor.attemptsTo(
                Get.resource("/v1/transactions/{id}")
                        .with(request -> request
                                .pathParam("id", transactionId)
                                .header(
                                        "Authorization",
                                        "Bearer " + config.privateKey()
                                )
                                .log().all()
                        )
        );

        SerenityRest.lastResponse().prettyPrint();

        actor.should(
                seeThatResponse("La transacción fue consultada correctamente",
                        response -> response
                                .statusCode(200)
                                .body("data.id", equalTo(transactionId))
                                .body("data.status", notNullValue())
                                .body("data.amount_in_cents", greaterThan(0))
                                .body("data.currency", notNullValue())
                )
        );
    }
}
