package com.wompi.api.setups;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class ActorSetUp {

    @Before(order = 0)
    public void setUpStage() {
        System.out.println(">>> CONFIGURANDO ACTOR Y API <<<");
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("ComercioWompi")
                .whoCan(
                        CallAnApi.at("https://api-sandbox.co.uat.wompi.dev")
                );
    }
}