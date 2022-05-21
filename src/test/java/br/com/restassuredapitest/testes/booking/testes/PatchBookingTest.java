package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceCriticalTest;
import br.com.restassuredapitest.suites.AcceptanceExceptionTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SchemaTest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.PatchBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.PostBookingRequest;
import br.com.restassuredapitest.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature de cadastro de reservas")
public class PatchBookingTest extends BaseTest {

    PatchBookingRequest patchBookingRequest = new PatchBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Fazer atualização parcial.")
    public void validarAtualizacaoParcial(){

        patchBookingRequest.bookingAlterParcialInfo(getBookingRequest.bookingFirstId(),"admin","password123",
                        "trocando","onome")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("firstname",equalTo("trocando"))
                .body("lastname",equalTo("onome"));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Fazer atualização parcial.")
    public void validarAtualizacaoParcialBasic(){

        patchBookingRequest.bookingAlterParcialInfoBasic(getBookingRequest.bookingFirstId(),
                        "trocando","onome")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("firstname",equalTo("trocando"))
                .body("lastname",equalTo("onome"));

    }


}
