package com.wompi.api.task;

import com.wompi.api.utils.SignatureUtils;
import com.wompi.api.utils.WompiConfig;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class CrearTransaccionBancaria implements Task {

    private final int amountInCents;
    private final String currency;
    private final String customerEmail;
    private final String reference;

    public CrearTransaccionBancaria(int amountInCents,
                                    String currency,
                                    String customerEmail,
                                    String reference) {
        this.amountInCents = amountInCents;
        this.currency = currency;
        this.customerEmail = customerEmail;
        this.reference = reference;
    }

    public static CrearTransaccionBancaria conDatosBasicos(
            int amountInCents,
            String currency,
            String customerEmail,
            String reference
    ) {
        return Tasks.instrumented(
                CrearTransaccionBancaria.class,
                amountInCents,
                currency,
                customerEmail,
                reference
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EnvironmentVariables environmentVariables =
                net.serenitybdd.core.Serenity.environmentVariables();

        WompiConfig config = new WompiConfig(environmentVariables);

        String acceptanceToken =
                actor.recall("ACCEPTANCE_TOKEN");

        String signature =
                SignatureUtils.generateSignature(
                        reference,
                        amountInCents,
                        currency,
                        config.integrityKey()
                );

        System.out.println(" Signature generada: " + signature);

        Map<String, Object> body = buildRequestBody(
                acceptanceToken,
                signature
        );

        actor.attemptsTo(
                Post.to("/v1/transactions")
                        .with(request -> request
                                .log().all()
                                .header("Authorization",
                                        "Bearer " + config.privateKey())
                                .contentType("application/json")
                                .body(body)
                        )
        );

        System.out.println("=============================");
        SerenityRest.lastResponse().prettyPrint();

        String transactionId =
                SerenityRest.lastResponse()
                        .jsonPath()
                        .getString("data.id");

        actor.remember("TRANSACTION_ID", transactionId);
    }
    private Map<String, Object> buildRequestBody(
            String acceptanceToken,
            String signature
    )
    {
        Map<String, Object> body = new HashMap<>();

        body.put("acceptance_token", acceptanceToken);
        body.put("amount_in_cents", amountInCents);
        body.put("currency", currency);
        body.put("customer_email", customerEmail);
        body.put("reference", reference);

        // 🔥 CLAVE — ahora sí viaja
        body.put("signature", signature);

        Map<String, Object> customerData = new HashMap<>();
        customerData.put("legal_id", "1144132574");
        customerData.put("full_name", "fabian restrepo a");
        customerData.put("phone_number", "3113676864");
        customerData.put("legal_id_type", "CC");

        body.put("customer_data", customerData);

        Map<String, Object> paymentMethod = new HashMap<>();
        paymentMethod.put("type", "BANCOLOMBIA_TRANSFER");
        paymentMethod.put("user_type", "PERSON");
        paymentMethod.put("payment_description", "Pago a Tienda Wompi");
        paymentMethod.put("ecommerce_url",
                "https://comercio.co/thankyou_page");

        body.put("payment_method", paymentMethod);

        return body;

    }

}
