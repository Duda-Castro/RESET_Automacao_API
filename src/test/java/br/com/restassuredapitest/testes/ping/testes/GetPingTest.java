package br.com.restassuredapitest.testes.ping.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.HealthCheck;
import br.com.restassuredapitest.testes.ping.requests.GetPingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

@Feature("Feature de api online")
public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, HealthCheck.class})
    @DisplayName("Verificar se a api está online")
    public void validarListagemDeIdsDasReservas(){

        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201);

    }


}
