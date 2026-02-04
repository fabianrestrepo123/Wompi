package com.wompi.api.stepdefinitions;

import com.wompi.api.questions.DatosDeLaTransaccion;
import com.wompi.api.questions.TransaccionExiste;
import com.wompi.api.task.ConsultarTransaccionBancaria;
import com.wompi.api.task.CrearTransaccionBancaria;
import com.wompi.api.task.ObtenerTokensLegales;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;



import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class TransaccionesStepDefinitions {

    @Given("el comercio esta habilitado para operar en Wompi")
    public void elComercioEstaHabilitadoParaOperarEnWompi() {
        theActorCalled("ComercioWompi");
    }

    @Given("el usuario  obtiene los tokens legales requeridos")
    public void elUsuarioObtieneLosTokensLegalesRequeridos() {
        theActorInTheSpotlight().attemptsTo(
                ObtenerTokensLegales.delMerchant()
        );
    }

    @When("el usuario crea una transaccion bancaria con datos validos")
    public void elUsuarioCreaUnaTransaccionBancariaConDatosValidos() {
        theActorInTheSpotlight().attemptsTo(
                CrearTransaccionBancaria.conDatosBasicos(
                        2490000,
                        "COP",
                        "fabianrestrepo@gmail.com",
                        "REF-" + System.currentTimeMillis()
            )
        );
    }

    @And("el usuario consulta la transaccion creada")
    public void elUsuarioConsultaLaTransaccionCreada() {
        theActorInTheSpotlight().attemptsTo(
                ConsultarTransaccionBancaria.porId()
        );
    }

    @Then("la transaccion existe y contiene la informacion esperada")
    public void laTransaccionExisteYContieneLaInformacionEsperada() {
        theActorInTheSpotlight().should(
                seeThat("La transacción existe",
                        TransaccionExiste.esValida(), is(true)),

                seeThat("Los datos de la transacción son correctos",
                        DatosDeLaTransaccion.sonCorrectos(), is(true))
        );
    }
}
