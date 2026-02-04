package com.wompi.api.runners;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        glue = {"com.wompi.api",
        "com.wompi.api.setups"},
        features = "src/test/resources/features/transaccion.feature",
        tags = "@smoke",
        snippets = CucumberOptions.SnippetType.CAMELCASE)


public class TransaccionesRunner {



}
